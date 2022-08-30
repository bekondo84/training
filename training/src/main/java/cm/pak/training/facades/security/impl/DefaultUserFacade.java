package cm.pak.training.facades.security.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.UserService;
import cm.pak.training.beans.security.UserData;
import cm.pak.training.facades.security.UserFacade;
import cm.pak.training.populators.security.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService ;
    @Autowired
    private UserPopulator userPopulator;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    public UserData create(UserData userData) throws ModelServiceException {
        UserModel user = userPopulator.revert(userData);
        user = userService.createOrUpdate(user);

        return userPopulator.popule(user);
    }

    @Override
    public List<UserData> getUsers() {
        List<UserModel> users = flexibleSearch.find("SELECT c FROM UserModel AS c");

        if (!CollectionUtils.isEmpty(users)) {
            return users.stream().map(u -> userPopulator.popule(u))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
