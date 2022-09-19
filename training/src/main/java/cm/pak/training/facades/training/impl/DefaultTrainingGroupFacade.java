package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingGroupModel;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.training.TrainingGroupData;
import cm.pak.training.facades.training.TrainingGroupFacade;
import cm.pak.training.populators.training.TrainingGroupPopulator;
import cm.pak.training.populators.training.TrainingSessionPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultTrainingGroupFacade implements TrainingGroupFacade {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultTrainingGroupFacade.class);
    public static final String GROUPS_FOR_SESSION = "SELECT c FROM TrainingGroupModel AS c WHERE c.session.id=%s";
    @Autowired
    private TrainingGroupPopulator populator;
    @Autowired
    private TrainingSessionPopulator sessionPopulator;
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    @Transactional
    public TrainingGroupData save(Long sessionId, TrainingGroupData source) throws ModelServiceException, ParseException {
        LOG.info(String.format("----------------------------------------- %s", source));
        final TrainingGroupModel data = populator.revert(source);
        final TrainingSessionModel sessionModel = modelService.find(TrainingSessionModel.class, sessionId);
        data.setSession(sessionModel);
        modelService.createOrUpdate(data);
        return source;
    }

    @Override
    public List<TrainingGroupData> getGroups(Long session) {
        List<TrainingGroupModel> data = flexibleSearch.find(String.format(GROUPS_FOR_SESSION, session));
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream()
                    .map(d -> populator.populate(d))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public TrainingGroupData getGroup(Long session, Long pk) {
        final TrainingGroupModel groupModel = flexibleSearch.find(TrainingGroupModel.class, pk);
        final TrainingGroupData groupData = populator.populate(groupModel);
        groupData.setSession(sessionPopulator.populate(groupModel.getSession()));
        return groupData;
    }

    @Transactional
    @Override
    public void remove(Long pk) {
         final TrainingGroupModel source = flexibleSearch.find(TrainingGroupModel.class, pk);
         modelService.remove(source);
    }
}
