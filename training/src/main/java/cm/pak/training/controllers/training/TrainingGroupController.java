package cm.pak.training.controllers.training;

import cm.pak.data.FilterData;
import cm.pak.data.PaginationData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingGroupModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.TrainingGroupData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.TrainingGroupFacade;
import cm.pak.training.populators.training.TrainingGroupPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sessionGroups")
@CrossOrigin
public class TrainingGroupController extends AbstractController{

    @Autowired
    private TrainingGroupFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private TrainingGroupPopulator populator;
    @Autowired
    private SettingFacade settingFacade;

    @GetMapping("/{session}")
    public ResponseEntity<PaginationData<TrainingGroupData>> groups(@PathVariable("session")Long session, @RequestParam(required = false) String search, @RequestParam(required = false) Integer page) {
        final PaginationData<TrainingGroupModel> pageable = searchData(TrainingGroupModel.class, page, settingFacade.getSetting().getPageSize(), buildSearchFilter(TrainingGroupData.class, search), new FilterData("session.pk", session, "eq"));
        return ResponseEntity.ok(populate(pageable));
    }

    @GetMapping("/{session}/{pk}")
    public ResponseEntity<TrainingGroupData> group(@PathVariable("session")Long session
            , @PathVariable("pk")Long pk) {
        return ResponseEntity.ok(facade.getGroup(session, pk));
    }

    @PostMapping("/{session}")
    public ResponseEntity<TrainingGroupData> save(@RequestBody TrainingGroupData source, @PathVariable("session")Long session) throws ModelServiceException, ParseException {
         return ResponseEntity.ok(facade.save(session, source));
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
