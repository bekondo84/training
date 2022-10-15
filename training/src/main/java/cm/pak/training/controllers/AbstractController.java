package cm.pak.training.controllers;

import cm.pak.annotations.SearchKeys;
import cm.pak.data.FilterData;
import cm.pak.data.PaginationData;
import cm.pak.models.security.base.ItemModel;
import cm.pak.populators.Populator;
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


     protected <T extends ItemModel> PaginationData<T> searchData(Class<T> clazz, Integer index, int pageSize, final List<FilterData> searchFilter, FilterData... filters) {
        return getFlexibleSearch().search(clazz, searchFilter, getStartValue(index), pageSize, filters);
    }

    private int getStartValue(Integer index) {
        return Objects.nonNull(index) ? index : 0;
    }

    private int getPageSize() {

        return 50;
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
                        try {
                            final Field field = clazz.getDeclaredField(searchKey.value());
                            if (String.class.isAssignableFrom(field.getType())) {
                                return new FilterData(searchKey.value(), "%".concat(searchtext).concat("%"), "like");
                            } else if (AbstractItemData.class.isAssignableFrom(field.getType())) {
                                final SearchKeys keys = field.getType().getAnnotation(SearchKeys.class);
                                if (Objects.nonNull(keys)) {
                                    searchFilters.addAll(Arrays.stream(keys.value())
                                            .map(key -> {
                                                try {
                                                    final Field field1 = field.getType().getDeclaredField(key.value());
                                                    if (String.class.isAssignableFrom(field1.getType())) {
                                                        return new FilterData(searchKey.value().concat(".").concat(key.value()), "%".concat(searchtext).concat("%"), "like");
                                                    }
                                                } catch (NoSuchFieldException e) {
                                                   // e.printStackTrace();
                                                }
                                                return null ;
                                            }).filter(Objects::nonNull)
                                            .collect(Collectors.toList()));
                                }
                            }
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
        }
        return searchFilters;
    }

   protected  <T extends AbstractItemData, Y extends ItemModel>PaginationData<T> populate(PaginationData<Y> page) {
        final PaginationData result = new PaginationData<>();
        result.setCurrentPage(page.getCurrentPage());
        result.setTotalPages(page.getTotalPages());
        if (!CollectionUtils.isEmpty(page.getItems())) {
            result.setItems(page.getItems()
                    .stream()
                    .map(item -> getPopulator().populate(item))
                    .collect(Collectors.toList()));
        }
        return  result;
    }
    abstract protected FlexibleSearch getFlexibleSearch() ;

    abstract protected  SettingFacade getSettingFacade() ;

    protected MetaService getMetaService() { return null ;};
     protected Populator getPopulator() {
        return null;
    }
}
