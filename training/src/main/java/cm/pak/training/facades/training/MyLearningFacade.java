package cm.pak.training.facades.training;

import cm.pak.training.beans.training.MyLearningData;

import java.util.List;

public interface MyLearningFacade {

    List<MyLearningData> getMyLearning(final String username) ;

    MyLearningData getMyLearning(final Long pk) ;
}
