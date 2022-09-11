package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingGroupModel;
import cm.pak.training.beans.training.TrainingGroupData;
import cm.pak.training.facades.training.TrainingGroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessionGroups")
public class TrainingGroupController {

    @Autowired
    private TrainingGroupFacade facade ;

    @GetMapping("/{session}")
    public ResponseEntity<List<TrainingGroupData>> groups(@PathVariable("session")Long session) {
        return ResponseEntity.ok(facade.getGroups(session));
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
}
