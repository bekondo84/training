package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.ClassRoomData;

import java.util.List;

public interface ClassRoomFacade {
    List<ClassRoomData> getClassRooms();
    ClassRoomData getClassRoom(final Long pk);
    ClassRoomData save(final ClassRoomData source) throws ModelServiceException;
    void remove(final Long pk);
}
