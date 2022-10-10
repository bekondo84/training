package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.ClassRoomModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.ClassRoomData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.ClassRoomFacade;
import cm.pak.training.populators.training.ClassRoomPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/classrooms")
@CrossOrigin
public class ClassRoomController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(ClassRoomController.class);
    @Autowired
    private ClassRoomFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private ClassRoomPopulator populator;

    @GetMapping
    public ResponseEntity<List<ClassRoomData>> getClassrooms(@RequestParam(required = false)String search) {
        final List<ClassRoomModel> classRooms = searchData(ClassRoomModel.class, null, 0, 50);
        LOG.info(String.format("---------------------------- searchParam : %s", search));
        if(!CollectionUtils.isEmpty(classRooms)) {
            return ResponseEntity.ok(classRooms
                    .stream()
                    .map(room -> populator.populate(room))
                    .collect(Collectors.toList()));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping("/{pk}")
    public ResponseEntity<ClassRoomData> getClassroom(@PathVariable("pk") final Long pk) {
           return ResponseEntity.ok(facade.getClassRoom(pk));
    }
    @PostMapping
    public ResponseEntity<ClassRoomData> save(@RequestBody ClassRoomData source) throws ModelServiceException {
        return ResponseEntity.ok(facade.save(source));
    }
    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk") Long pk) {
        facade.remove(pk);
    }

    @Override
    protected FlexibleSearch getFlexibleSearch() {
        return flexibleSearch;
    }

    @Override
    protected SettingFacade getSettingFacade() {
        return null;
    }
}
