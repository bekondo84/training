package cm.pak.training.populators.training;

import cm.pak.models.training.InvolvedModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.populators.security.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InvolvePopulator implements Populator<InvolvedModel, InvolvedData> {

    @Autowired
    private UserPopulator userPopulator;
    @Autowired
    private TrainingSessionPopulator sessionPopulator;


    @Override
    public InvolvedData populate(InvolvedModel source) {
        final InvolvedData data = new InvolvedData();
        populate(source, data);
        if (Objects.nonNull(source.getInvolve())){
            data.setInvolve(userPopulator.populate(source.getInvolve()));
            data.setValue(data.getInvolve().getValue());
        }
        if (Objects.nonNull(source.getSession())) {
            data.setSession(sessionPopulator.populate(source.getSession()));
        }
        data.setRole(source.getRole());
        data.setPk(source.getPk());
        data.setRegistered(source.isRegistred());
        return data;
    }

    @Override
    public InvolvedModel revert(InvolvedData source) {
        final InvolvedModel data = new InvolvedModel();
        revert(source, data);
        if (Objects.nonNull(source.getInvolve())){
            data.setInvolve(userPopulator.revert(source.getInvolve()));
        }
        if (Objects.nonNull(source.getSession())) {
            data.setSession(sessionPopulator.revert(source.getSession()));
        }
        data.setRole(source.getRole());
        data.setPk(source.getPk());
        data.setRegistred(source.isRegistered());
        return data;
    }
}
