package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalConfig(creatable = false,updatable = false,deletable = false)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 1),
        @Group(name = "learner", label = "learner.group", sequence = 3),
        @Group(name = "planing", label = "planing.group", sequence = 4)
})
@SearchKey(value = "code", label = "code")
public class MyLearningData extends AbstractItemData implements Serializable {
    @Widget(value = "text", group = "general", column = true, updatable = false)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String intitule;
    @Manytoone(group = "general", column = true, source = "/api/v1/trainings")
    private TrainingData training;
    @Widget(value = "date", group = "general" ,column = true)
    private String startAt;
    @Widget(value = "date", group = "general", column = true)
    private String endAt ;
    @Widget(value = "checkbox", group = "general", column = true)
    private boolean registered ;
    @Onetomany(group = "planing", editable = false, updatable = false, deletable = false, source = "/api/v1/sessionGroups")
    private List<MyLearningGroupData> groups ;

    public MyLearningData() {
       groups = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public TrainingData getTraining() {
        return training;
    }

    public void setTraining(TrainingData training) {
        this.training = training;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public List<MyLearningGroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<MyLearningGroupData> groups) {
        this.groups = groups;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    @Override
    public String getTargetEntity() {
        return TrainingSessionModel.class.getName();
    }
}
