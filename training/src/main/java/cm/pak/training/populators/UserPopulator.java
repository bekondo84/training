package cm.pak.training.populators;

import cm.pak.models.security.UserModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.UserData;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<UserModel, UserData> {

    @Override
    public UserData popule(UserModel source) {
        final UserData data = new UserData();

        data.setPk(source.getPk());
        data.setUpdateOn(source.getCreate());
        data.setUpdateOn(source.getUpdate());
        data.setCategory(source.getCategorie());

        return data;
    }

    @Override
    public UserModel revert(UserData source) {
        final UserModel model = new UserModel();
        model.setPk(source.getPk());
        model.setCategorie(source.getCategory());
        model.setCode(source.getCode());
        model.setGenre(source.getGenre());
        return model;
    }
}
