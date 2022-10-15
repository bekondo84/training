package cm.pak.training.controllers.training;

import cm.pak.data.PaginationData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.training.TrainingThemeModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.training.TrainingThemeData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.training.TrainingThemeFacade;
import cm.pak.training.populators.training.TrainingThemePopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/themes")
@CrossOrigin
public class TrainingThemeController extends AbstractController {

    @Autowired
    private TrainingThemeFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private TrainingThemePopulator populator;
    @Autowired
    private SettingFacade settingFacade;


    @GetMapping
    public ResponseEntity<PaginationData<TrainingThemeData>> getThemes(@RequestParam(required = false) String search, @RequestParam(required = false) Integer page) {
        final PaginationData<TrainingThemeModel> pageable = searchData(TrainingThemeModel.class, page, settingFacade.getSetting().getPageSize(), buildSearchFilter(TrainingThemeData.class, search));
        return ResponseEntity.ok(populate(pageable));
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
