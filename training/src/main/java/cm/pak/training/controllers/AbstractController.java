package cm.pak.training.controllers;

import cm.pak.annotations.SearchKeys;
import cm.pak.data.FilterData;
import cm.pak.models.security.base.ItemModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.MetaService;
import cm.pak.training.beans.AbstractItemData;
import cm.pak.training.beans.training.TrainingData;
import cm.pak.training.facades.core.SettingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    @RequestMapping("/search/{page}/{searchKey}")
    public ResponseEntity getItemBySearchKey(final @PathVariable("page") Integer page, final @PathVariable("searchKey")  String search) {
        return ResponseEntity.ok(null);
    }

    protected List<FilterData> filtersBuilder(List<String> filter) {
        final List<FilterData> filters = new ArrayList<>();
        Optional.ofNullable(filter)
                .ifPresent(rules-> filters.addAll(rules
                        .stream()
                        .map(rule -> {
                            final String[] parts = rule.split("-");
                            return new FilterData(parts[0], getMetaService().convert(TrainingData.class,parts[0], parts[2]), parts[1]);
                        }).collect(Collectors.toList())));
        return filters;
    }

    @ResponseBody
    @DeleteMapping("/api/v1/{id}")
    public void delete(@PathVariable("id") Long id) {

    }


     protected <T extends ItemModel> List<T> searchData(Class<T> clazz, int index, int pageSize, final List<FilterData> searchFilter, FilterData... filters) {
        return getFlexibleSearch().search(clazz, searchFilter, index, pageSize, filters);
    }

    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T extends AbstractItemData> List<FilterData> buildSearchFilter(Class<T> clazz, final String searchtext) {
        final List<FilterData> searchFilters = new ArrayList<>();
        final SearchKeys searchKeys = clazz.getAnnotation(SearchKeys.class);

        if (Objects.nonNull(searchKeys) && StringUtils.hasLength(searchtext)) {
            searchFilters.addAll(Arrays.stream(searchKeys.value())
                    .map(searchKey -> {
                        final FilterData filter =new FilterData(searchKey.value(), searchtext, "eq");
                        try {
                            final Field field = clazz.getDeclaredField(filter.getField());
                            if (String.class.isAssignableFrom(field.getType())) {
                                filter.setOperator("like");
                                filter.setValue("%".concat(searchtext).concat("%"));
                            } else if(field.getType().isPrimitive()) {
                                filter.setOperator("eq");
                            }else if (Boolean.class.isAssignableFrom(field.getType())) {
                                filter.setOperator("eq");
                            } else if(Number.class.isAssignableFrom(field.getType())) {
                                filter.setOperator("eq");
                            }
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        return filter;
                    })
                    .collect(Collectors.toList()));
        }
        return searchFilters;
    }

    abstract protected FlexibleSearch getFlexibleSearch() ;

    abstract protected  SettingFacade getSettingFacade() ;

    protected MetaService getMetaService() { return null ;};
}
