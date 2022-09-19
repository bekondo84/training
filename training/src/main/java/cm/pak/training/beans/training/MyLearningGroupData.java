package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.TrainingGroupModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group"),
        @Group(name = "timesheet", label = "timesheet.group"),
        @Group(name = "registered", label = "registered.group")
})
public class MyLearningGroupData extends AbstractItemData {
    @Widget(value = "text", column = true, group = "general")
    private String code ;
    @Manytoone(group = "general", editable = false, source = "/api/v1/sessions")
    private TrainingSessionData session;
    @Manytoone(group = "general", column = true, source = "/api/v1/classrooms")
    private ClassRoomData classRoom;
    @Widget(value = "number", group = "general", column = true, editable = false)
    private Integer numberOfPlaces;
    @Widget(value = "date", group = "general", column = true)
    private String startAt;
    @Widget(value = "date", group = "general", column = true)
    private String endAt;
    @Widget(value = "number", group = "general", column = true, editable = false)
    private Integer availablePlaces;
    @Widget(value = "number", group = "general", column = true, editable = false)
    private Integer reservePlaces;
    @Onetomany(group = "timesheet")
    private Set<TimeSheetItemData> timesheet ;

    public MyLearningGroupData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(Integer numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
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

    public Set<TimeSheetItemData> getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Set<TimeSheetItemData> timesheet) {
        this.timesheet = timesheet;
    }

    @Override
    public String getTargetEntity() {
        return TrainingGroupModel.class.getName();
    }
}
