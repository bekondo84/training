package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingThemeModel;
import cm.pak.training.beans.training.TrainingThemeData;

import java.util.List;

public interface TrainingThemeFacade {

    List<TrainingThemeData> getThemes() ;
    TrainingThemeData save(final TrainingThemeData source) throws ModelServiceException;
    void remove(final Long pk);
    TrainingThemeData getTheme(Long pk) ;
}
