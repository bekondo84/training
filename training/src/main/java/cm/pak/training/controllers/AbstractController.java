package cm.pak.training.controllers;

import cm.pak.data.FilterData;
import cm.pak.models.security.base.ItemModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.MetaService;
import cm.pak.training.beans.training.TrainingData;
import cm.pak.training.facades.core.SettingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


     protected <T extends ItemModel> List<T> searchData(Class<T> clazz, String searchtext, int index, int pageSize, FilterData... filters) {
        // final SettingData setting = getSettingFacade().getSetting();
        return getFlexibleSearch().search(clazz, searchtext , new ArrayList<>(), index, pageSize, filters);
    }

    abstract protected FlexibleSearch getFlexibleSearch() ;

    abstract protected  SettingFacade getSettingFacade() ;

    protected MetaService getMetaService() { return null ;};
}
