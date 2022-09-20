package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.MyLearningData;
import cm.pak.training.beans.training.MyLearningGroupData;
import cm.pak.training.controllers.CoreController;
import cm.pak.training.facades.training.MyLearningFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mylearning")
public class MyLearningController {
    private static final Logger LOG = LoggerFactory.getLogger(CoreController.class);
    @Autowired
    private MyLearningFacade facade ;

    @GetMapping
    public ResponseEntity<List<MyLearningData>> getMyLearningForAuthentication(Authentication authentication) {
       return ResponseEntity.ok(facade.getMyLearning(authentication.getName()));
    }

    @GetMapping("/{pk}")
    public ResponseEntity<MyLearningData> getMyLearning(@PathVariable("pk")Long pk) {
        return ResponseEntity.ok(facade.getMyLearning(pk));
    }

    @PostMapping("/{pk}")
    public ResponseEntity<List<MyLearningData>> register(@PathVariable("pk")Long groupPk
            , @RequestBody MyLearningData source) throws ModelServiceException {
        final MyLearningGroupData group = source.getGroups()
                                                .stream()
                                                .filter(gr -> gr.getPk().equals(groupPk))
                                                .findAny().get();
        return ResponseEntity.ok(facade.register(source, group));
    }
}
