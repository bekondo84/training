package cm.pak.training.controllers.training;

import cm.pak.data.PaginationData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.ClassRoomModel;
import cm.pak.populators.Populator;
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
    @Autowired
    private SettingFacade settingFacade;

    @GetMapping
    public ResponseEntity<PaginationData<ClassRoomData>> getClassrooms(@RequestParam(required = false)String search, @RequestParam(required = false) Integer page) {
        final PaginationData<ClassRoomModel> pagination = searchData(ClassRoomModel.class, page, settingFacade.getSetting().getPageSize(), buildSearchFilter(ClassRoomData.class, search));
        final PaginationData<ClassRoomData> result = populate(pagination);
        return ResponseEntity.ok(result);
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

    @Override
    protected Populator getPopulator() {
        return populator;
    }
}
