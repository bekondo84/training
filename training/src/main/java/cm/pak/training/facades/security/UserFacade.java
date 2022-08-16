package cm.pak.training.facades.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.UserData;

public interface UserFacade {

     UserData create(final UserData user) throws ModelServiceException;
}
