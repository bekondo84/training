package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.TrainingThemeData;
import cm.pak.training.facades.training.TrainingThemeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/themes")
public class TrainingThemeController {

    @Autowired
    private TrainingThemeFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;


    @GetMapping
    public ResponseEntity<List<TrainingThemeData>> getThemes() {
         return ResponseEntity.ok(facade.getThemes());
    }

    @PostMapping
    public ResponseEntity<TrainingThemeData> save(@RequestBody TrainingThemeData source) throws ModelServiceException {
        return ResponseEntity.ok(facade.save(source));
    }

    @GetMapping("/{pk}")
    public ResponseEntity<TrainingThemeData> getTheme(@PathVariable("pk") Long pk) {
         return ResponseEntity.ok(facade.getTheme(pk));
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk")Long pk) {
        facade.remove(pk);
    }
}
