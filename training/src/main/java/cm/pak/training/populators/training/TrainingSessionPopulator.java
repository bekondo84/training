package cm.pak.training.populators.training;

import cm.pak.models.training.TrainingSessionModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.populators.security.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TrainingSessionPopulator implements Populator<TrainingSessionModel, TrainingSessionData> {

    @Autowired
    private TrainingPopulator populator;
    @Autowired
    private UserPopulator userPopulator;

    @Override
    public TrainingSessionData populate(TrainingSessionModel source) {
        final TrainingSessionData data = new TrainingSessionData();
        populate(source, data);
        data.setCode(source.getCode());
        data.setEndAt(source.getEndAt());
        data.setStartAt(source.getStartAt());
        data.setIntitule(source.getIntitule());
        data.setValue(source.getIntitule());
        if (Objects.nonNull(source.getTraining())) {
            data.setTraining(populator.populate(source.getTraining()));
        }
        return data;
    }

    @Override
    public TrainingSessionModel revert(TrainingSessionData source) {
        final TrainingSessionModel data = new TrainingSessionModel();
        revert(source, data);
        data.setCode(source.getCode());
        data.setEndAt(source.getEndAt());
        data.setStartAt(source.getStartAt());
        data.setIntitule(source.getIntitule());
        if (Objects.nonNull(source.getTraining())) {
            data.setTraining(populator.revert(source.getTraining()));
        }

        return data;
    }
}
