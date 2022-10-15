package cm.pak.training.controllers.training;

import cm.pak.data.FilterData;
import cm.pak.data.PaginationData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.InvolvedModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.beans.training.MyLearningData;
import cm.pak.training.beans.training.MyLearningGroupData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.controllers.CoreController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.MyLearningFacade;
import cm.pak.training.populators.training.MyLearningPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/mylearning")
@CrossOrigin
public class MyLearningController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(CoreController.class);
    @Autowired
    private MyLearningFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private MyLearningPopulator populator;
    @Autowired
    private SettingFacade settingFacade;

    @GetMapping
    public ResponseEntity<PaginationData<MyLearningData>> getMyLearningForAuthentication(@RequestParam(required = false) String search, @RequestParam(required = false) Integer page, Authentication authentication) {
       final PaginationData<InvolvedModel> pageable = searchData(InvolvedModel.class, 0, -1, buildSearchFilter(MyLearningData.class, search), new FilterData("involve.code", authentication.getName(), "eq"), new FilterData("session.statut", "P", "eq"));
       final PaginationData<MyLearningData> result = populate(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{pk}")
    public ResponseEntity<MyLearningData> getMyLearning(@PathVariable("pk")Long pk) {
        return ResponseEntity.ok(facade.getMyLearning(pk));
    }


    @PostMapping("/register/{pk}")
    public ResponseEntity<List<MyLearningData>> register(@PathVariable("pk")Long groupPk
            , @RequestBody MyLearningData source) throws ModelServiceException {
        final MyLearningGroupData group = source.getGroups()
                                                .stream()
                                                .filter(gr -> gr.getPk().equals(groupPk))
                                                .findAny().get();
        return ResponseEntity.ok(facade.register(source, group));
    }
    @PostMapping("/unregister")
    public ResponseEntity<List<MyLearningData>> unRegister(@RequestBody MyLearningData source) throws ModelServiceException {
        return ResponseEntity.ok(facade.unRegister(source));
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
