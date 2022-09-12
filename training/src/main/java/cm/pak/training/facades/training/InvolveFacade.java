package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.beans.training.TrainingSessionData;

import java.text.ParseException;
import java.util.List;

public interface InvolveFacade {

    InvolvedData save(final Long session, final InvolvedData involve) throws ModelServiceException, ParseException;

    List<InvolvedData> getInvolves(final Long session);

    InvolvedData getInvolve(final Long pk);

    void remove(final Long pk) ;
}
