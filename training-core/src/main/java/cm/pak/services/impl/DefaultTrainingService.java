package cm.pak.services.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultTrainingService implements TrainingService {
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    @Transactional
    public TrainingModel save(TrainingModel source) throws ModelServiceException {
        modelService.createOrUpdate(source);
        return flexibleSearch.find(TrainingModel.class, "name", source.getName());
    }

    @Override
    @Transactional
    public TrainingModel activate(TrainingModel source) throws ModelServiceException {
        if (!source.isActivate()) {
            source.setActivate(Boolean.TRUE);
            modelService.createOrUpdate(source);
        }
        return source;
    }

    @Override
    @Transactional
    public TrainingModel desactivate(TrainingModel source) throws ModelServiceException {
        if (source.isActivate()) {
            source.setActivate(Boolean.FALSE);
            modelService.createOrUpdate(source);
        }
        return source;
    }
}
