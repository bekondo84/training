package cm.pak.training.beans.training;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.training.TimeSheetItemModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group")
})
public class TimeSheetItemData extends AbstractItemData implements Serializable {
    @NotNull
    @Widget(value = "date", group = "general", column = true, nullable = false)
    private String day ;
    @NotNull
    @Widget(value = "time", group = "general", column = true, nullable = false)
    private String startAt;
    @NotNull
    @Widget(value = "time", group = "general", column = true, nullable = false)
    private String endAt;
    @NotNull
    @Widget(value = "text", group = "general", column = true, nullable = false)
    private String subject;

    public TimeSheetItemData() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSheetItemData data = (TimeSheetItemData) o;
        return Objects.equals(day, data.day) && Objects.equals(startAt, data.startAt) && Objects.equals(endAt, data.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, startAt, endAt);
    }

    @Override
    public String getTargetEntity() {
        return TimeSheetItemModel.class.getName();
    }
}
