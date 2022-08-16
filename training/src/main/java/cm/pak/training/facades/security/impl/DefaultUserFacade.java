package cm.pak.training.facades.security.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.services.UserService;
import cm.pak.training.beans.UserData;
import cm.pak.training.facades.security.UserFacade;
import cm.pak.training.populators.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService ;

    @Autowired
    private UserPopulator userPopulator;

    @Override
    public UserData create(UserData userData) throws ModelServiceException {
        UserModel user = userPopulator.revert(userData);
        user = userService.createOrUpdate(user);

        return userPopulator.popule(user);
    }
}
