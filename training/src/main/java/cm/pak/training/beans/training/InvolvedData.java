package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.InvolvedModel;
import cm.pak.training.beans.AbstractData;
import cm.pak.training.beans.security.UserData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 2)
})
public class InvolvedData extends AbstractData implements Serializable {
    @Manytoone(group = "general", editable = false, source = "/api/v1/sessions")
    private TrainingSessionData session ;
    @Manytoone(group = "general",column = true, source = "/api/v1/users")
    private UserData involve;
    @Select(group = "general", value = {
            @SelectItem(value = "T",name = "teacher"),@SelectItem(value = "L", name = "learner")
    }, column = true)
    private String role ;
    @Manytoone(group = "general", column = true, sequence = 10, source = "/api/v1/classrooms")
    private ClassRoomData classRoom;
    @Widget(value = "checkbox", column = true,group = "general", editable = false)
    private boolean registered ;

    public InvolvedData() {
        this.registered = false;
    }

    public TrainingSessionData getSession() {
        return session;
    }

    public void setSession(TrainingSessionData session) {
        this.session = session;
    }

    public ClassRoomData getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoomData classRoom) {
        this.classRoom = classRoom;
    }

    public UserData getInvolve() {
        return involve;
    }

    public void setInvolve(UserData involve) {
        this.involve = involve;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getTargetEntity() {
        return InvolvedModel.class.getName();
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
