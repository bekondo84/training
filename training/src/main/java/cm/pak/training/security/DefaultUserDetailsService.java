package cm.pak.training.security;

import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

public class DefaultUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserModel user = flexibleSearch.find(UserModel.class, "code", username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("Could not found account with userna"));
        }
        return new cm.pak.training.security.UserDetails(user);
    }
}
