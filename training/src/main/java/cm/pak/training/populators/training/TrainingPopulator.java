package cm.pak.training.populators.training;

import cm.pak.models.training.TrainingModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.TrainingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TrainingPopulator implements Populator<TrainingModel, TrainingData> {

    @Autowired
    private TrainingThemePopulator populator ;

    @Override
    public TrainingData populate(TrainingModel source) {
        TrainingData data = new TrainingData();
        populate(source, data);
        data.setDescription(source.getDescription());
        data.setImage(source.getImage());
        data.setName(source.getName());
        data.setSequence(source.getSequence());
        data.setFullDescription(source.getFullDescription());
        data.setValue(source.getDescription());
        data.setActivate(source.isActivate());
        if (Objects.nonNull(source.getTheme())) {
            data.setTheme(populator.populate(source.getTheme()));
        }
        return data;
    }

    @Override
    public TrainingModel revert(TrainingData source) {
        TrainingModel data = new TrainingModel();
        revert(source, data);
        data.setDescription(source.getDescription());
        data.setImage(source.getImage());
        data.setName(source.getName());
        data.setSequence(source.getSequence());
        data.setActivate(source.isActivate());
        data.setFullDescription(source.getFullDescription());
        if (Objects.nonNull(source.getTheme())) {
            data.setTheme(populator.revert(source.getTheme()));
        }
        return data;
    }
}
