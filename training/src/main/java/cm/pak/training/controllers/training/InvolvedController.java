package cm.pak.training.controllers.training;

import cm.pak.data.FilterData;
import cm.pak.data.PaginationData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.InvolvedModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.InvolveFacade;
import cm.pak.training.populators.training.InvolvePopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/leaners")
@CrossOrigin
public class InvolvedController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(InvolvedController.class);
    @Autowired
    private InvolveFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private InvolvePopulator populator ;
    @Autowired
    private SettingFacade settingFacade;

    @GetMapping("/{session}")
    public ResponseEntity<PaginationData<InvolvedData>> getInvolveForSessionID(@PathVariable("session")Long sessoin, @RequestParam(required = false) final String search, @RequestParam(required = false) Integer page) {
        PaginationData<InvolvedModel> pageable = searchData(InvolvedModel.class, page, settingFacade.getSetting().getPageSize(), buildSearchFilter(InvolvedData.class, search), new FilterData("session.pk", sessoin, "eq"));
        PaginationData<InvolvedData> result = populate(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{session}")
    public ResponseEntity<InvolvedData> save(@PathVariable("session")Long session, @RequestBody InvolvedData source) throws ModelServiceException, ParseException {
        return ResponseEntity.ok(facade.save(session, source));
    }

    @GetMapping("/{session}/{pk}")
    public ResponseEntity<InvolvedData> getInvolve(@PathVariable("session") Long session, @PathVariable("pk") Long pk){
        return ResponseEntity.ok(facade.getInvolve(pk));
    }

    @DeleteMapping("/{session}/{pk}")
    public void remove(@PathVariable("session") Long session, @PathVariable("pk") Long pk) {
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
