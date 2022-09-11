package cm.pak.services;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingModel;

public interface TrainingService {

    TrainingModel save(final TrainingModel source) throws ModelServiceException;
    TrainingModel activate(final TrainingModel source) throws ModelServiceException;
    TrainingModel desactivate(final TrainingModel source) throws ModelServiceException;
}
