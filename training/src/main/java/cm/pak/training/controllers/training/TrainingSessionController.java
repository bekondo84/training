package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingSessionModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.exceptions.TrainingException;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.TrainingSessionFacade;
import cm.pak.training.populators.training.TrainingSessionPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sessions")
@CrossOrigin
public class TrainingSessionController extends AbstractController {

    @Autowired
    private TrainingSessionFacade facade;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private TrainingSessionPopulator populator;

    @GetMapping
    public ResponseEntity<List<TrainingSessionData>> getSessions(@RequestParam(required = false) String search) {
        final List<TrainingSessionModel> sessions = searchData(TrainingSessionModel.class, 0, 50, buildSearchFilter(TrainingSessionData.class, search));

        if (!CollectionUtils.isEmpty(sessions)) {
            return ResponseEntity.ok(sessions.stream()
                    .map(session -> populator.populate(session))
                    .collect(Collectors.toList()));
        }
         return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping("/{pk}")
    public ResponseEntity<TrainingSessionData> getSession(@PathVariable("pk") Long pk) {
        return ResponseEntity.ok(facade.getSession(pk));
    }
    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk")Long pk) {
        facade.remove(pk);
    }
    @PostMapping
    public ResponseEntity<TrainingSessionData> save(@RequestBody TrainingSessionData source) throws ModelServiceException, ParseException {
        final TrainingSessionData data = facade.save(source);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/leaner/{session}")
    public ResponseEntity<List<InvolvedData>> getInvolvedData(@PathVariable("session") Long session) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/publish")
    public ResponseEntity<TrainingSessionData> publish(@RequestBody TrainingSessionData source) throws ModelServiceException, ParseException, TrainingException {
             return ResponseEntity.ok(facade.publish(source));
    }

    @PostMapping("/unpublish")
    public ResponseEntity<TrainingSessionData> unpublish(@RequestBody TrainingSessionData source) throws ModelServiceException, ParseException, TrainingException {
        return ResponseEntity.ok(facade.unpublish(source));
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
