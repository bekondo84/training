package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.TrainingGroupData;

import java.text.ParseException;
import java.util.List;

public interface TrainingGroupFacade {
    TrainingGroupData save(final Long sessionId, final TrainingGroupData source) throws ModelServiceException, ParseException;
    List<TrainingGroupData> getGroups(final Long session);
    TrainingGroupData getGroup(final Long session, final Long pk);
    void remove(final Long pk) ;
}
