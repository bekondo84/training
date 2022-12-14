package cm.pak.training.facades.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.security.SetPasswordData;
import cm.pak.training.beans.security.UserData;
import cm.pak.training.exceptions.TrainingException;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserFacade {

     UserData create(final UserData user) throws ModelServiceException;
     List<UserData> getUsers() ;
     UserData getUser(final Long pk);
     void remove(final Long pk);
     UserData setPassword(final Long pk, final SetPasswordData data) throws TrainingException, ModelServiceException;
     UserData setPassword(final Long pk, final String password) throws ModelServiceException;
     void createAdminUser() throws ModelServiceException;
     void updateUsersFromInputStream(final InputStream is, String type, final List<String> headers) throws ModelServiceException, UnsupportedEncodingException;
}
