package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.exceptions.TrainingException;

import java.text.ParseException;
import java.util.List;

public interface TrainingSessionFacade {
    List<TrainingSessionData> getSessions();
    TrainingSessionData getSession(final Long pk);
    TrainingSessionData save(final TrainingSessionData source) throws ModelServiceException, ParseException;
    void remove(final Long pk);
    TrainingSessionData publish(final TrainingSessionData source) throws ParseException, ModelServiceException, TrainingException;
    TrainingSessionData unpublish(final TrainingSessionData source) throws ParseException, ModelServiceException;
}
