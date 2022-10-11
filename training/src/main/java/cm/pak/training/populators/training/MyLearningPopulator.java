package cm.pak.training.populators.training;

import cm.pak.models.training.InvolvedModel;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.MyLearningData;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.populators.security.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Component
public class MyLearningPopulator implements Populator<InvolvedModel, MyLearningData> {
    private final static SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    @Autowired
    private TrainingPopulator populator;
    @Autowired
    private UserPopulator userPopulator;
    @Autowired
    private TrainingGroupPopulator groupPopulator;

    @Override
    public MyLearningData populate(InvolvedModel source) {
        final MyLearningData data = new MyLearningData();
        populate(source, data);
        data.setCode(source.getSession().getCode());
        if (Objects.nonNull(source.getSession().getEndAt())) {
            data.setEndAt(SDF.format(source.getSession().getEndAt()));
        }
        if (Objects.nonNull(source.getSession().getStartAt())) {
            data.setStartAt(SDF.format(source.getSession().getStartAt()));
        }
        data.setIntitule(source.getSession().getIntitule());
        data.setValue(source.getSession().getIntitule());
        data.setRegistered(source.isRegistred());
        if (Objects.nonNull(source.getSession().getTraining())) {
            data.setTraining(populator.populate(source.getSession().getTraining()));
        }
        if (Objects.nonNull(source.getGroup())) {
            data.setRegisterGroup(groupPopulator.populate(source.getGroup()));
        }
        return data;
    }

    @Override
    public InvolvedModel revert(MyLearningData source) throws ParseException {
        final InvolvedModel data = new InvolvedModel();

        return data;
    }
}
