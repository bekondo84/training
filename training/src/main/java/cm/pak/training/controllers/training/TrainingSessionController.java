package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.beans.training.TrainingSessionData;
import cm.pak.training.facades.training.TrainingSessionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionFacade facade;

    @GetMapping
    public ResponseEntity<List<TrainingSessionData>> getSessions() {
         return ResponseEntity.ok(facade.getSessions());
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
    public ResponseEntity<TrainingSessionData> save(@RequestBody TrainingSessionData source) throws ModelServiceException {
        final TrainingSessionData data = facade.save(source);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/leaner/{session}")
    public ResponseEntity<List<InvolvedData>> getInvolvedData(@PathVariable("session") Long session) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}