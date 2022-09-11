package cm.pak.training.facades.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.security.UserData;

import java.util.List;

public interface UserFacade {

     UserData create(final UserData user) throws ModelServiceException;
     List<UserData> getUsers() ;
     UserData getUser(final Long pk);
     void remove(final Long pk);
}
