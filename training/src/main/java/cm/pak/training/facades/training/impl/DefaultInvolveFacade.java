package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.InvolvedModel;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.facades.training.InvolveFacade;
import cm.pak.training.populators.training.InvolvePopulator;
import cm.pak.training.populators.training.TrainingSessionPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultInvolveFacade implements InvolveFacade {
    public static final String INVOLVES_FOR_SESSIONS = "SELECT c FROM InvolvedModel AS c WHERE c.session.id = %s";
    public static final String INVOLVE_FOR_SESSION_AND_USER = "SELECT c FROM InvolvedModel AS c WHERE c.session.id=%s AND c.involve.id=%s";
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private InvolvePopulator populator ;
    @Autowired
    private TrainingSessionPopulator sessionPopulator;


    @Override
    @Transactional
    public InvolvedData save(final Long session, final InvolvedData involve) throws ModelServiceException {
        final TrainingSessionData sessionData = sessionPopulator.populate(flexibleSearch.find(TrainingSessionModel.class, session));
        involve.setSession(sessionData);
        final InvolvedModel data = populator.revert(involve);
        modelService.createOrUpdate(data);
        List<InvolvedModel> result= flexibleSearch.find(String.format(INVOLVE_FOR_SESSION_AND_USER, involve.getSession().getPk(), involve.getInvolve().getPk()));
        return populator.populate(result.get(0));
    }

    @Transactional
    @Override
    public void remove(Long pk) {
        final InvolvedModel data = modelService.find(InvolvedModel.class, pk);
        modelService.remove(data);
    }

    @Override
    public List<InvolvedData> getInvolves(final Long sessionPk) {
        final TrainingSessionData sessionData = sessionPopulator.populate(flexibleSearch.find(TrainingSessionModel.class, sessionPk));
        List<InvolvedModel> data = flexibleSearch.find(String.format(INVOLVES_FOR_SESSIONS, sessionPk));
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().map(d -> {
                final InvolvedData val = populator.populate(d);
                val.setSession(sessionData);
                return val;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public InvolvedData getInvolve(Long pk) {
        final InvolvedModel involve = flexibleSearch.find(InvolvedModel.class, pk);
        final TrainingSessionData sessionData = sessionPopulator.populate(flexibleSearch.find(TrainingSessionModel.class, involve.getSession().getPk()));
        final InvolvedData data = populator.populate(involve);
        data.setSession(sessionData);
        return data ;
    }
}
