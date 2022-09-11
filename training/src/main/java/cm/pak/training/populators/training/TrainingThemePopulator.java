package cm.pak.training.populators.training;

import cm.pak.models.training.TrainingThemeModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.TrainingThemeData;
import org.springframework.stereotype.Component;

@Component
public class TrainingThemePopulator implements Populator<TrainingThemeModel, TrainingThemeData> {

    @Override
    public TrainingThemeData populate(TrainingThemeModel source) {
        final TrainingThemeData data = new TrainingThemeData();
        populate(source, data);
        data.setCode(source.getCode());
        data.setDescription(source.getDescription());
        data.setName(source.getName());
        data.setValue(source.getName());
        return data;
    }

    @Override
    public TrainingThemeModel revert(TrainingThemeData source) {
        final TrainingThemeModel data = new TrainingThemeModel();
        revert(source, data);
        data.setCode(source.getCode());
        data.setDescription(source.getDescription());
        data.setName(source.getName());
        return data;
    }
}
