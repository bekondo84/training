package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.facades.training.TrainingSessionFacade;
import cm.pak.training.populators.training.InvolvePopulator;
import cm.pak.training.populators.training.TrainingGroupPopulator;
import cm.pak.training.populators.training.TrainingSessionPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingSessionFacadeImpl implements TrainingSessionFacade {
    public static final String SESSION_LEANERS = "SELECT c FROM TrainingSessionModel AS c JOIN FETCH c.learners WHERE c.pk=%s";
    public static final String SESSION_GROUPS = "SELECT d FROM TrainingGroupModel AS d WHERE d.session.pk=%s";
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private TrainingSessionPopulator populator ;
    @Autowired
    private InvolvePopulator involvePopulator;
    @Autowired
    private TrainingGroupPopulator groupPopulator;

    @Override
    public List<TrainingSessionData> getSessions() {
        final List<TrainingSessionModel> data = flexibleSearch.find("SELECT c FROM TrainingSessionModel AS c");

        if (!CollectionUtils.isEmpty(data)) {
            return data.stream()
                    .map(s -> {
                        s.setGroups(flexibleSearch.find(String.format(SESSION_GROUPS, s.getPk())));
                        return populator.populate(s);
                    }).
                    collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public TrainingSessionData getSession(Long pk) {
        final List<TrainingSessionModel> data = flexibleSearch.find(String.format(SESSION_LEANERS, pk));
        final TrainingSessionData session =  populator.populate(data.get(0));
        if (!CollectionUtils.isEmpty(data.get(0).getLearners())) {
            session.setLearners(data.get(0).getLearners().stream()
                    .map(d -> involvePopulator.populate(d))
                    .collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(data.get(0).getGroups())) {
            session.setGroups(data.get(0).getGroups()
                    .stream()
                    .map(d -> groupPopulator.populate(d))
                    .collect(Collectors.toList()));
        }
        return session;
    }

    @Override
    @Transactional
    public TrainingSessionData save(TrainingSessionData source) throws ModelServiceException, ParseException {
        TrainingSessionModel data = populator.revert(source);
        modelService.createOrUpdate(data);
        data = flexibleSearch.find(TrainingSessionModel.class, "code", source.getCode());
        return populator.populate(data);
    }

    @Override
    @Transactional
    public void remove(Long pk) {
        final TrainingSessionModel data = modelService.find(TrainingSessionModel.class, pk);
        modelService.remove(data);
    }
}
