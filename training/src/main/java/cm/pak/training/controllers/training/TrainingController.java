package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.TrainingData;
import cm.pak.training.facades.training.TrainingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingController {

    @Autowired
    private TrainingFacade facade ;

    @GetMapping
    public ResponseEntity<List<TrainingData>> getTrainings() {
        return ResponseEntity.ok(facade.getTrainigs());
    }

    @PostMapping
    public ResponseEntity<TrainingData> save(@RequestBody TrainingData data) throws ModelServiceException {
        return ResponseEntity.ok(facade.save(data)) ;
    }

    @GetMapping("/{pk}")
    public ResponseEntity<TrainingData> getTraining(@PathVariable("pk")Long pk) {
        return ResponseEntity.ok(facade.getTrainig(pk));
    }
    @DeleteMapping("/{pk}")
    public  void delete(@PathVariable("pk")Long pk) {
        facade.remove(pk);
    }

    @PostMapping("/activate")
    public ResponseEntity<TrainingData> activate(@RequestBody TrainingData source) throws ModelServiceException {
          return ResponseEntity.ok(facade.activate(source));
    }

    @PostMapping("/desactivate")
    public ResponseEntity<TrainingData> desactivate(@RequestBody TrainingData source) throws ModelServiceException {
        return ResponseEntity.ok(facade.desactivate(source));
    }
}
