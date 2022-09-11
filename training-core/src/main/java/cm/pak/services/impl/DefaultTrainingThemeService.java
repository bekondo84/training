package cm.pak.services.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingThemeModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.TrainingThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultTrainingThemeService implements TrainingThemeService {
    @Autowired
    private ModelService modelService ;
    @Autowired
    private FlexibleSearch flexibleSearch;


    @Override
    public List<TrainingThemeModel> getThemes() {
        return flexibleSearch.find("SELECT c FROM TrainingThemeModel AS c ");
    }

    @Override
    @Transactional
    public TrainingThemeModel save(TrainingThemeModel source) throws ModelServiceException {
        modelService.createOrUpdate(source);
        return flexibleSearch.find(TrainingThemeModel.class, "code", source.getCode());
    }

    @Override
    @Transactional
    public void remove(Long pk) {
       final TrainingThemeModel source = modelService.find(TrainingThemeModel.class, pk);
       modelService.remove(source);
    }
}
