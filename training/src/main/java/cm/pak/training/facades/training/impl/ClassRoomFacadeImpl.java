package cm.pak.training.facades.training.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.ClassRoomModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.training.ClassRoomData;
import cm.pak.training.facades.training.ClassRoomFacade;
import cm.pak.training.populators.training.ClassRoomPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassRoomFacadeImpl implements ClassRoomFacade {

    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private ClassRoomPopulator populator;


    @Override
    public List<ClassRoomData> getClassRooms() {
        List<ClassRoomModel> data = flexibleSearch.find("SELECT c FROM ClassRoomModel AS c");
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream()
                    .map(d -> populator.populate(d))
                    .collect(Collectors.toList());
        }
        return  new ArrayList<>();
    }

    @Override
    public ClassRoomData getClassRoom(Long pk) {
        final ClassRoomModel data = flexibleSearch.find(ClassRoomModel.class, pk);
        return populator.populate(data);
    }

    @Transactional
    @Override
    public ClassRoomData save(ClassRoomData source) throws ModelServiceException {
        final ClassRoomModel data = populator.revert(source);
        modelService.createOrUpdate(data);
        return populator.populate(flexibleSearch.find(ClassRoomModel.class, "code", source.getCode()));
    }

    @Transactional
    @Override
    public void remove(Long pk) {
        final ClassRoomModel data = flexibleSearch.find(ClassRoomModel.class, pk);
        modelService.remove(data);
    }
}
