package cm.pak.services;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;

public interface UserService {

    UserModel createOrUpdate(final UserModel user) throws ModelServiceException;
}
