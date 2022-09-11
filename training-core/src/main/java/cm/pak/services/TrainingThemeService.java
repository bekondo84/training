package cm.pak.services;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingThemeModel;

import java.util.List;

public interface TrainingThemeService {

    List<TrainingThemeModel> getThemes() ;
    TrainingThemeModel save(final TrainingThemeModel source) throws ModelServiceException;
    void remove(final Long pk);
}
