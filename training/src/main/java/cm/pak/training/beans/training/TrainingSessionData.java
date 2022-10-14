package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 1),
        @Group(name = "learner", label = "learner.group", sequence = 3),
        @Group(name = "planing", label = "planing.group", sequence = 4)
})
@SearchKeys({
        @SearchKey(value = "code", label = "code", primary = true),
        @SearchKey(value = "intitule", label = "intitule"),
        @SearchKey(value = "training", label = "training"),
        @SearchKey(value = "statut", label = "statut"),
        @SearchKey(value = "startAt", label = "startAt"),
        @SearchKey(value = "endAt", label = "endAt")
})
public class TrainingSessionData extends AbstractItemData implements Serializable {
    @NotNull
    @Widget(value = "text", group = "general", column = true, updatable = false)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String intitule;
    @NotNull
    @Filters(@Filter(field = "activate", value = "true"))
    @Manytoone(group = "general", column = true, source = "/api/v1/trainings")
    private TrainingData training;
    @NotNull
    @Select(group = "general", value = {
            @SelectItem(value = "P", name = "Publiée"),
            @SelectItem(value = "C", name = "Masquée")
    }, editable = false, column = true)
    private String statut ;
    @NotNull
    @Widget(value = "date", group = "general" ,column = true)
    private String startAt;
    @NotNull
    @Widget(value = "date", group = "general", column = true)
    private String endAt ;
    @Onetomany(group = "learner", editable = false, updatable = false, deletable = false, source = "/api/v1/plugins")
    private List<InvolvedData> learners;
    @Onetomany(group = "planing", editable = false, updatable = false, deletable = false, source = "/api/v1/sessionGroups")
    private List<TrainingGroupData> groups ;

    public TrainingSessionData() {
        learners = new ArrayList<>();
        statut ="C";
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

    public List<InvolvedData> getLearners() {
        return learners;
    }

    public void setLearners(List<InvolvedData> learners) {
        this.learners = learners;
    }

    public List<TrainingGroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<TrainingGroupData> groups) {
        this.groups = groups;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String getTargetEntity() {
        return TrainingSessionModel.class.getName();
    }
}
