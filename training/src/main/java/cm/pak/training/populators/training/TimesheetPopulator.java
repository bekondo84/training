package cm.pak.training.populators.training;

import cm.pak.models.training.TimeSheetItemModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.training.TimeSheetItemData;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;


@Component
public class TimesheetPopulator implements Populator<TimeSheetItemModel, TimeSheetItemData> {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public TimeSheetItemData populate(TimeSheetItemModel source) {
        final TimeSheetItemData data = new TimeSheetItemData();
        populate(source, data);
        if (Objects.nonNull(source.getDay())) {
            data.setDay(SDF.format(source.getDay()));
        }
        data.setEndAt(source.getEndAt());
        data.setStartAt(source.getStartAt());
        data.setSubject(source.getSubject());
        return data;
    }

    @Override
    public TimeSheetItemModel revert(TimeSheetItemData source) throws ParseException {
        final TimeSheetItemModel data = new TimeSheetItemModel();
        revert(source, data);
        if (Objects.nonNull(source.getDay())) {
            data.setDay(SDF.parse(source.getDay()));
        }
        data.setEndAt(source.getEndAt());
        data.setStartAt(source.getStartAt());
        data.setSubject(source.getSubject());
        return data;
    }
}
