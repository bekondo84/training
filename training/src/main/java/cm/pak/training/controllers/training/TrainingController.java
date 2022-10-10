package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.TrainingData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.TrainingFacade;
import cm.pak.training.populators.training.TrainingPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/trainings")
@CrossOrigin
public class TrainingController extends AbstractController {

    @Autowired
    private TrainingFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private TrainingPopulator populator;

    @GetMapping
    public ResponseEntity<List<TrainingData>> getTrainings() {
        final List<TrainingModel> trainings = searchData(TrainingModel.class,null , 0, 50);

        if (!CollectionUtils.isEmpty(trainings)) {
            return ResponseEntity.ok(trainings.stream()
                    .map(training -> populator.populate(training))
                    .collect(Collectors.toList()));
        }
        return ResponseEntity.ok(new ArrayList<>());
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

    @Override
    protected FlexibleSearch getFlexibleSearch() {
        return flexibleSearch;
    }

    @Override
    protected SettingFacade getSettingFacade() {
        return null;
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
