package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingThemeModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.TrainingThemeService;
import cm.pak.training.beans.training.TrainingThemeData;
import cm.pak.training.facades.training.TrainingThemeFacade;
import cm.pak.training.populators.training.TrainingThemePopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingThemeFacadeImpl implements TrainingThemeFacade {

    @Autowired
    private TrainingThemePopulator populator ;
    @Autowired
    private TrainingThemeService service;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    public List<TrainingThemeData> getThemes() {
        final List<TrainingThemeModel> data = service.getThemes();
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream()
                    .map(d ->populator.populate(d))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public TrainingThemeData getTheme(Long pk) {
        final TrainingThemeModel source = flexibleSearch.find(TrainingThemeModel.class, pk);
        return populator.populate(source);
    }

    @Override
    public TrainingThemeData save(TrainingThemeData source) throws ModelServiceException {
        final TrainingThemeModel data = service.save(populator.revert(source));
        return populator.populate(data);
    }

    @Override
    public void remove(Long pk) {
        service.remove(pk);
    }
}
