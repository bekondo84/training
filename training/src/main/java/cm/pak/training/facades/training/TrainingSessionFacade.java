package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.TrainingSessionData;

import java.util.List;

public interface TrainingSessionFacade {
    List<TrainingSessionData> getSessions();
    TrainingSessionData getSession(final Long pk);
    TrainingSessionData save(final TrainingSessionData source) throws ModelServiceException;
    void remove(final Long pk);
}
