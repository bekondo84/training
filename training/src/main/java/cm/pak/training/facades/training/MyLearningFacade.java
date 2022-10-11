package cm.pak.training.facades.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.MyLearningData;
import cm.pak.training.beans.training.MyLearningGroupData;

import java.util.List;

public interface MyLearningFacade {

    List<MyLearningData> getMyLearning(final String username) ;

    MyLearningData getMyLearning(final Long pk) ;

    List<MyLearningData> register(final MyLearningData myLearning, final MyLearningGroupData group) throws ModelServiceException;
    List<MyLearningData> unRegister(final MyLearningData myLearning) throws ModelServiceException;
}
