package cm.pak.training.populators.security;

import cm.pak.models.security.UserModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.security.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserPopulator implements Populator<UserModel, UserData> {
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Override
    public UserData populate(UserModel source) {
        final UserData data = new UserData();
        populate(source, data);
        data.setCategory(source.getCategorie());
        data.setCode(source.getCode());
        data.setGenre(source.getGenre());
        data.setName(source.getName());
        data.setEmail(source.getEmail());
        data.setValue(source.getName());
        return data;
    }

    @Override
    public UserModel revert(UserData source) {
        UserModel model = new UserModel();
        revert(source, model);
        model.setCategorie(source.getCategory());
        model.setCode(source.getCode());
        model.setGenre(source.getGenre());
        model.setName(source.getName());
        model.setEmail(source.getEmail());
        return model;
    }
}
