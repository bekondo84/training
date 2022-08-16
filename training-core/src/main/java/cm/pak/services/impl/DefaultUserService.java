package cm.pak.services.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.ModelService;
import cm.pak.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private ModelService modelService;


    @Override
    public UserModel createOrUpdate(UserModel user) throws ModelServiceException {
        return modelService.createOrUpdate(user) ;
    }
}
