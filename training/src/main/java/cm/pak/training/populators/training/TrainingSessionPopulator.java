package cm.pak.training.populators.training;

import cm.pak.models.training.TrainingSessionModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.populators.security.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Component
public class TrainingSessionPopulator implements Populator<TrainingSessionModel, TrainingSessionData> {
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private TrainingPopulator populator;
    @Autowired
    private UserPopulator userPopulator;

    @Override
    public TrainingSessionData populate(TrainingSessionModel source) {
        final TrainingSessionData data = new TrainingSessionData();
        populate(source, data);
        data.setCode(source.getCode());
        if (Objects.nonNull(source.getEndAt())) {
            data.setEndAt(SDF.format(source.getEndAt()));
        }
        if (Objects.nonNull(source.getStartAt())) {
            data.setStartAt(SDF.format(source.getStartAt()));
        }
        data.setIntitule(source.getIntitule());
        data.setValue(source.getIntitule());
        if (Objects.nonNull(source.getTraining())) {
            data.setTraining(populator.populate(source.getTraining()));
        }
        data.setStatut(source.getStatut());
        if (!StringUtils.hasLength(source.getStatut())) {
            data.setStatut("C");
        }
        return data;
    }

    @Override
    public TrainingSessionModel revert(TrainingSessionData source) throws ParseException {
        final TrainingSessionModel data = new TrainingSessionModel();
        revert(source, data);
        data.setCode(source.getCode());
        if (StringUtils.hasLength(source.getEndAt())) {
            data.setEndAt(SDF.parse(source.getEndAt()));
        }
        if (StringUtils.hasLength(source.getStartAt())) {
            data.setStartAt(SDF.parse(source.getStartAt()));
        }
        data.setIntitule(source.getIntitule());
        if (Objects.nonNull(source.getTraining())) {
            data.setTraining(populator.revert(source.getTraining()));
        }
        data.setStatut(source.getStatut());
        return data;
    }
}
