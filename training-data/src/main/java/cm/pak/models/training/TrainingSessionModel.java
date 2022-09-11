package cm.pak.models.training;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_trse")
public class TrainingSessionModel extends ItemModel implements Serializable {
    @Column(name = "t_code", unique = true)
    private String code ;
    @Column(name = "t_inti")
    private String intitule;
    @ManyToOne
    @JoinColumn(name = "t_trai")
    private TrainingModel training;
    @Temporal(TemporalType.DATE)
    @Column(name = "t_star")
    private Date startAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "t_end")
    private Date endAt ;
    @OneToMany(mappedBy = "session")
    private List<InvolvedModel> learners ;
    @OneToMany(mappedBy = "session")
    private List<TrainingGroupModel> groups ;

    public TrainingSessionModel() {
        learners = new ArrayList<>();
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

    public TrainingModel getTraining() {
        return training;
    }

    public void setTraining(TrainingModel training) {
        this.training = training;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public List<InvolvedModel> getLearners() {
        return learners;
    }

    public void setLearners(List<InvolvedModel> learners) {
        this.learners = learners;
    }

    public List<TrainingGroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<TrainingGroupModel> groups) {
        this.groups = groups;
    }
}
