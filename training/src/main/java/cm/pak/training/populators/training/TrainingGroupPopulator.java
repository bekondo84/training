package cm.pak.training.populators.training;

import cm.pak.models.training.TrainingGroupModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.TrainingGroupData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Component
public class TrainingGroupPopulator implements Populator<TrainingGroupModel, TrainingGroupData> {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ClassRoomPopulator classRoomPopulator;

    @Override
    public TrainingGroupData populate(TrainingGroupModel source) {
        final TrainingGroupData data = new TrainingGroupData();
        populate(source, data);
        data.setCode(source.getCode());
        data.setReservePlaces(0);
        data.setNumberOfPlaces(0);
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
        return data;
    }

    @Override
    public TrainingGroupModel revert(TrainingGroupData source) throws ParseException {
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
        return data;
    }
}
