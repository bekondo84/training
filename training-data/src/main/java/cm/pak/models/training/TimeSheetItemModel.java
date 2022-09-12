package cm.pak.models.training;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_tish")
public class TimeSheetItemModel extends ItemModel implements Serializable {
    @Column(name = "t_day")
    @Temporal(TemporalType.DATE)
    private Date day ;
    @Column(name = "t_star")
    private String startAt;
    @Column(name = "t_endA")
    private String endAt;
    @Column(name = "t_subj")
    private String subject;

    public TimeSheetItemModel() {
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
