package cm.pak.training.populators.training;

import cm.pak.models.training.ClassRoomModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.ClassRoomData;
import org.springframework.stereotype.Component;

@Component
public class ClassRoomPopulator implements Populator<ClassRoomModel, ClassRoomData> {
    @Override
    public ClassRoomData populate(ClassRoomModel source) {
        final ClassRoomData data = new ClassRoomData();
        populate(source, data);
        data.setPk(source.getPk());
        data.setCode(source.getCode());
        data.setIntitule(source.getIntitule());
        data.setValue(source.getIntitule());
        data.setAbility(source.getAbility());
        data.setAdresse(source.getAdresse());
        data.setLocalisation(source.getLocalisation());
        return data;
    }

    @Override
    public ClassRoomModel revert(ClassRoomData source) {
        final ClassRoomModel data = new ClassRoomModel();
        revert(source, data);
        data.setCode(source.getCode());
        data.setIntitule(source.getIntitule());
        data.setAbility(source.getAbility());
        data.setAdresse(source.getAdresse());
        data.setLocalisation(source.getLocalisation());
        return data;
    }
}
