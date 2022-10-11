package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.InvolvedModel;
import cm.pak.models.training.TrainingGroupModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.training.MyLearningData;
import cm.pak.training.beans.training.MyLearningGroupData;
import cm.pak.training.facades.training.MyLearningFacade;
import cm.pak.training.populators.training.MyLearningGroupPopulator;
import cm.pak.training.populators.training.MyLearningPopulator;
import cm.pak.training.populators.training.TrainingGroupPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultMyLearningFacade implements MyLearningFacade {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMyLearningFacade.class);
    public static final String SESSION_GROUPS = "SELECT d FROM TrainingGroupModel AS d WHERE d.session.pk=%s";
    public static final String CURRENT_USER_TRAINING = "SELECT i FROM InvolvedModel AS i WHERE i.involve.code='%s' AND i.session.statut='%s'";
    @Autowired
    private MyLearningPopulator populator;
    @Autowired
    private MyLearningGroupPopulator groupPopulator;
    @Autowired
    private FlexibleSearch flexibleSearch ;
    @Autowired
    private ModelService modelService;


    @Override
    public List<MyLearningData> getMyLearning(final String username) {
        final List<InvolvedModel> data = flexibleSearch.find(String.format(CURRENT_USER_TRAINING, username, "P"));
        if (!CollectionUtils.isEmpty(data)) {
           return data
                   .stream()
                   .map(d ->populator.populate(d))
                   .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public MyLearningData getMyLearning(Long pk) {
        MyLearningData myLearning = new MyLearningData() ;
        final InvolvedModel student = flexibleSearch.find(InvolvedModel.class, pk);
        myLearning = populator.populate(student);
        List<TrainingGroupModel> groups = flexibleSearch.find(String.format(SESSION_GROUPS, student.getSession().getPk()));

        if (!CollectionUtils.isEmpty(groups)) {
            myLearning.setGroups(groups
                    .stream()
                    .map(gr -> groupPopulator.populate(gr))
                    .collect(Collectors.toList()));
        }

        return myLearning;
    }

    @Override
    @Transactional
    public List<MyLearningData> register(MyLearningData myLearning, MyLearningGroupData group) throws ModelServiceException {
        final TrainingGroupModel trainingGroup = flexibleSearch.find(TrainingGroupModel.class, group.getPk());
        final InvolvedModel involve = flexibleSearch.find(InvolvedModel.class, myLearning.getPk());
        involve.setRegistred(Boolean.TRUE);
        involve.setGroup(trainingGroup);
        involve.setRegistredDate(new Date());
        modelService.createOrUpdate(involve);
        //Group
        trainingGroup.register(involve.getInvolve());
        modelService.createOrUpdate(trainingGroup);
        return getMyLearning(involve.getInvolve().getCode());
    }

    @Override
    @Transactional
    public List<MyLearningData> unRegister(MyLearningData myLearning) throws ModelServiceException {
        LOG.info(String.format("---------------------------------------- %s", myLearning));
        final InvolvedModel involve = flexibleSearch.find(InvolvedModel.class, myLearning.getPk());
        final TrainingGroupModel trainingGroup = involve.getGroup();
        involve.setRegistred(Boolean.FALSE);
        involve.setGroup(null);
        involve.setRegistredDate(null);
        modelService.createOrUpdate(involve);
        //Group
        trainingGroup.unRegister(involve.getInvolve());
        modelService.createOrUpdate(trainingGroup);
        return getMyLearning(involve.getInvolve().getCode());
    }
}
