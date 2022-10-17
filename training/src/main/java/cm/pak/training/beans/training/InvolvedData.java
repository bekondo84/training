package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.InvolvedModel;
import cm.pak.training.beans.AbstractItemData;
import cm.pak.training.beans.security.UserData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 2)
})
@SearchKeys({
        @SearchKey(value = "session", label = "session"),
        @SearchKey(value = "involve", label = "involve", primary = true)
})
public class InvolvedData extends AbstractItemData implements Serializable {
    @Manytoone(group = "general", editable = false, source = "/api/v1/sessions")
    private TrainingSessionData session ;
    @NotNull
    @Manytoone(group = "general",column = true, source = "/api/v1/users")
    private UserData involve;
    @NotNull
    @Select(group = "general", value = {
            @SelectItem(value = "T",name = "teacher"),@SelectItem(value = "L", name = "learner")
    }, column = true)
    private String role ;

    @Manytoone(group = "general", column = true, sequence = 10, editable = false, source = "/api/v1/groups")
    private TrainingGroupData group;

    @Widget(value = "date", group = "general", column = true, editable = false)
    private String registredDate ;

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

    public TrainingGroupData getGroup() {
        return group;
    }

    public void setGroup(TrainingGroupData group) {
        this.group = group;
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

    public String getRegistredDate() {
        return registredDate;
    }

    public void setRegistredDate(String registredDate) {
        this.registredDate = registredDate;
    }
}
