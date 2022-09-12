package cm.pak.training.beans.training;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.training.TimeSheetItemModel;
import cm.pak.training.beans.AbstractItemData;

import java.io.Serializable;
import java.util.Date;

@Groups({
        @Group(name = "general", label = "general.group")
})
public class TimeSheetItemData extends AbstractItemData implements Serializable {
    @Widget(value = "date", group = "general", column = true)
    private Date day ;
    @Widget(value = "time", group = "general", column = true)
    private String startAt;
    @Widget(value = "time", group = "general", column = true)
    private String endAt;
    @Widget(value = "text", group = "general", column = true)
    private String subject;

    public TimeSheetItemData() {
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

    @Override
    public String getTargetEntity() {
        return TimeSheetItemModel.class.getName();
    }
}
