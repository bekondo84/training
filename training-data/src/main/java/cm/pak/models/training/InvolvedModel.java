package cm.pak.models.training;

import cm.pak.models.security.UserModel;
import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_invo")
public class InvolvedModel extends ItemModel implements Serializable {
    @ManyToOne
    @JoinColumn(name = "t_user")
    private UserModel involve ;
    @ManyToOne
    @JoinColumn(name = "t_sess")
    private TrainingSessionModel session;
    @Column(name = "t_role")
    private String role ;
    @Column(name = "t_regi")
    private boolean registred ;
    @Column(name = "t_reda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registredDate ;
    @ManyToOne
    @JoinColumn(name = "t_trgr")
    private TrainingGroupModel group;

    public InvolvedModel() {
    }

    public UserModel getInvolve() {
        return involve;
    }

    public void setInvolve(UserModel involve) {
        this.involve = involve;
    }

    public TrainingSessionModel getSession() {
        return session;
    }

    public void setSession(TrainingSessionModel session) {
        this.session = session;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isRegistred() {
        return registred;
    }

    public void setRegistred(boolean registred) {
        this.registred = registred;
    }

    public TrainingGroupModel getGroup() {
        return group;
    }

    public void setGroup(TrainingGroupModel group) {
        this.group = group;
    }

    public Date getRegistredDate() {
        return registredDate;
    }

    public void setRegistredDate(Date registredDate) {
        this.registredDate = registredDate;
    }
}
