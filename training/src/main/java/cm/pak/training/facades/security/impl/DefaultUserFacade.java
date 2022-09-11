package cm.pak.training.facades.security.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.security.UserData;
import cm.pak.training.facades.security.UserFacade;
import cm.pak.training.populators.security.GroupePopulaor;
import cm.pak.training.populators.security.UserPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserFacade implements UserFacade {
    public static final Logger LOG = LoggerFactory.getLogger(DefaultUserFacade.class);
    public static final String USER_FOR_PK = "SELECT u FROM UserModel AS u JOIN FETCH u.profils WHERE u.pk=%s";
    @Autowired
    private ModelService modelService ;
    @Autowired
    private UserPopulator userPopulator;
    @Autowired
    private GroupePopulaor groupePopulaor;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Transactional
    @Override
    public UserData create(UserData userData) throws ModelServiceException {
        UserModel user = userPopulator.revert(userData);
        if (!CollectionUtils.isEmpty(userData.getProfils())) {
            userData.getProfils().forEach(gr -> user.addProfil(groupePopulaor.revert(gr)));
        }
        modelService.createOrUpdate(user);
        return userPopulator.populate(flexibleSearch.find(UserModel.class, "code", userData.getCode()));
    }

    @Override
    public List<UserData> getUsers() {
        List<UserModel> users = flexibleSearch.find("SELECT c FROM UserModel AS c");

        if (!CollectionUtils.isEmpty(users)) {
            return users.stream().map(u -> userPopulator.populate(u))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public UserData getUser(Long pk) {
        //LOG.info("------------------------------- : "+String.format(USER_FOR_PK, pk));
        final UserModel userModel = flexibleSearch.find(UserModel.class, pk);
        final UserData data = userPopulator.populate(userModel);

        if (!CollectionUtils.isEmpty(userModel.getProfils())) {
            data.setProfils(userModel.getProfils()
                    .stream()
                    .map(u -> groupePopulaor.populate(u))
                    .collect(Collectors.toList()));
        }
        return data;
    }

    @Transactional
    @Override
    public void remove(Long pk) {
       final UserModel user = flexibleSearch.find(UserModel.class, pk);
       modelService.remove(user);
    }
}
