package cm.pak.training.populators.training;

import cm.pak.models.training.TrainingGroupModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.security.UserData;
import cm.pak.training.beans.training.MyLearningGroupData;
import cm.pak.training.beans.training.TimeSheetItemData;
import cm.pak.training.beans.training.TrainingGroupData;
import cm.pak.training.populators.security.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MyLearningGroupPopulator implements Populator<TrainingGroupModel, MyLearningGroupData> {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ClassRoomPopulator classRoomPopulator;
    @Autowired
    private TimesheetPopulator timesheetPopulator;
    @Autowired
    private UserPopulator userPopulator;

    @Override
    public MyLearningGroupData populate(TrainingGroupModel source) {
        final MyLearningGroupData data = new MyLearningGroupData();
        populate(source, data);
        data.setCode(source.getCode());
        data.setReservePlaces(0);
        data.setNumberOfPlaces(0);
        if (Objects.nonNull(source.getReservePlaces())) {
            data.setReservePlaces(source.getReservePlaces());
        }
        if (Objects.nonNull(source.getClassRoom())) {
            data.setClassRoom(classRoomPopulator.populate(source.getClassRoom()));
            data.setNumberOfPlaces(source.getClassRoom().getAbility());
        }
        data.setAvailablePlaces(data.getNumberOfPlaces()- data.getReservePlaces());
        if (Objects.nonNull(source.getStartAt())) {
            data.setEndAt(SDF.format(source.getEndAt()));
        }
        if (Objects.nonNull(source.getEndAt())) {
            data.setStartAt(SDF.format(source.getStartAt()));
        }
        if (!CollectionUtils.isEmpty(source.getTimeSheet())) {
            data.setTimesheet(source.getTimeSheet()
                    .stream()
                    .map(time -> timesheetPopulator.populate(time))
                    .collect(Collectors.toSet()));
        }
        data.setValue(String.format("%s - %s",source.getCode(), source.getClassRoom().getIntitule()));
        return data;
    }

    @Override
    public TrainingGroupModel revert(MyLearningGroupData source) throws ParseException {
        final TrainingGroupModel data = new TrainingGroupModel();
        revert(source, data);
        data.setCode(source.getCode());
        if (Objects.nonNull(source.getClassRoom())) {
            data.setClassRoom(classRoomPopulator.revert(source.getClassRoom()));
            data.setNumberOfPlaces(data.getClassRoom().getAbility());
        }
        if (Objects.isNull(data.getReservePlaces())) {
            data.setReservePlaces(0);
        }
        data.setAvailablePlaces(data.getNumberOfPlaces() - data.getReservePlaces());
        if (StringUtils.hasLength(source.getStartAt())) {
            data.setStartAt(SDF.parse(source.getStartAt()));
        }
        if (StringUtils.hasLength(source.getEndAt())) {
            data.setEndAt(SDF.parse(source.getEndAt()));
        }
        if (!CollectionUtils.isEmpty(source.getTimesheet())) {
            for (TimeSheetItemData elt : source.getTimesheet()) {
                data.getTimeSheet().add(timesheetPopulator.revert(elt));
            }
        }
        return data;
    }
}
