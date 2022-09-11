package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.TrainingService;
import cm.pak.training.beans.training.TrainingData;
import cm.pak.training.facades.training.TrainingFacade;
import cm.pak.training.populators.training.TrainingPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingFacadeImpl implements TrainingFacade {

    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private TrainingPopulator populator;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private ModelService modelService;

    @Override
    public List<TrainingData> getTrainigs() {
        List<TrainingModel> datas = flexibleSearch.find("SELECT c FROM TrainingModel AS c ");

        if (!CollectionUtils.isEmpty(datas)) {
            return datas.stream()
                    .map(d ->populator.populate(d))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public TrainingData getTrainig(Long pk) {
        final TrainingModel source =  flexibleSearch.find(TrainingModel.class, pk);
        return populator.populate(source);
    }

    @Override
    public TrainingData save(TrainingData source) throws ModelServiceException {
        final TrainingModel data = populator.revert(source);
        return  populator.populate(trainingService.save(data));
    }

    @Override
    @Transactional
    public void remove(Long pk) {
        final TrainingModel training = modelService.find(TrainingModel.class, pk);
        modelService.remove(training);
    }

    @Override
    public TrainingData activate(TrainingData source) throws ModelServiceException {
        final TrainingModel data = trainingService.activate(populator.revert(source));
        return populator.populate(data);
    }

    @Override
    public TrainingData desactivate(TrainingData source) throws ModelServiceException {
        final TrainingModel data = trainingService.desactivate(populator.revert(source));
        return populator.populate(data);
    }
}
