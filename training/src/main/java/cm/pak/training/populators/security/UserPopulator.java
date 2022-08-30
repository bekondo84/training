package cm.pak.training.populators.security;

import cm.pak.models.security.UserModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.security.UserData;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<UserModel, UserData> {

    @Override
    public UserData popule(UserModel source) {
        final UserData data = new UserData();

        data.setPk(source.getPk());
        data.setCreate(source.getCreate());
        data.setUpdate(source.getUpdate());
        data.setCategory(source.getCategorie());
        data.setCode(source.getCode());
        data.setGenre(source.getGenre());
        data.setName(source.getName());
        data.setValue(source.getName());
        return data;
    }

    @Override
    public UserModel revert(UserData source) {
        final UserModel model = new UserModel();
        model.setPk(source.getPk());
        model.setCategorie(source.getCategory());
        model.setCode(source.getCode());
        model.setGenre(source.getGenre());
        model.setUpdate(source.getUpdate());
        model.setName(source.getName());
        model.setCreate(source.getCreate());
        return model;
    }
}
