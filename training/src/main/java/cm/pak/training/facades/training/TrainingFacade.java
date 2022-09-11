package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.TrainingData;

import java.util.List;

public interface TrainingFacade {

    List<TrainingData> getTrainigs() ;
    TrainingData getTrainig(final Long pk);
    TrainingData save(final TrainingData source) throws ModelServiceException;
    void remove(final Long pk);
    TrainingData activate(final TrainingData source) throws ModelServiceException;
    TrainingData desactivate(final TrainingData source) throws ModelServiceException;
}
