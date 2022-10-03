package cm.pak.models.training;

import cm.pak.models.security.UserModel;
import cm.pak.models.security.base.ItemModel;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_trgr")
public class TrainingGroupModel extends ItemModel implements Serializable {
    @Column(name = "t_code")
    private String code ;
    @ManyToOne
    @JoinColumn(name = "t_clro")
    private ClassRoomModel classRoom;
    @ManyToOne
    @JoinColumn(name = "t_sess")
    private TrainingSessionModel session;
    @Column(name = "t_nbpl")
    private Integer numberOfPlaces;
    @Column(name = "t_acpl")
    private Integer availablePlaces;
    @Column(name = "t_repl")
    private Integer reservePlaces;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "regis_user_tb"
            , joinColumns = @JoinColumn(name = "t_grou")
            ,inverseJoinColumns = @JoinColumn(name = "t_user"))
    private Set<UserModel> registered;
    @Column(name = "t_star", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startAt;
    @Column(name = "t_endA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endAt;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "t_tish")
    private Set<TimeSheetItemModel> timeSheet;

    public TrainingGroupModel() {
        registered = new HashSet<>();
        timeSheet = new HashSet<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClassRoomModel getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoomModel classRoom) {
        this.classRoom = classRoom;
    }

    public TrainingSessionModel getSession() {
        return session;
    }

    public void setSession(TrainingSessionModel session) {
        this.session = session;
    }

    public Integer getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(Integer numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public Integer getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Integer getReservePlaces() {
        return reservePlaces;
    }

    public void setReservePlaces(Integer reservePlaces) {
        this.reservePlaces = reservePlaces;
    }

    public Set<UserModel> getRegistered() {
        return Collections.unmodifiableSet(registered);
    }

    public void setRegistered(Set<UserModel> registered) {
        this.registered = registered;
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

    public Set<TimeSheetItemModel> getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(Set<TimeSheetItemModel> timeSheet) {
        this.timeSheet = timeSheet;
    }

    public void register(final UserModel user) {
        this.reservePlaces++;
        this.availablePlaces--;
        this.registered.add(user);
    }

    public void unRegister(final UserModel user) {
        this.reservePlaces--;
        this.availablePlaces++;
        this.registered.remove(user);
    }
}
