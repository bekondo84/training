package cm.pak.training.controllers.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.GroupeModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.security.GroupeData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.security.GroupeFacade;
import cm.pak.training.populators.security.GroupePopulaor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/groups")
@CrossOrigin
public class GroupeController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(GroupeController.class);
     @Autowired
    private GroupeFacade facade;
    @Autowired
    protected ModelService modelService;
    @Autowired
    private GroupePopulaor populaor;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private SettingFacade settingFacade;

    @GetMapping
    public ResponseEntity<List<GroupeData>> getGroupes(@RequestParam(required = false) String search) {
        List<GroupeModel> groupes = searchData(GroupeModel.class, 0, 50, buildSearchFilter(GroupeData.class, search));
        if (!CollectionUtils.isEmpty(groupes)) {
           return ResponseEntity.ok(groupes.stream()
                   .map(grp -> populaor.populate(grp))
                   .collect(Collectors.toList()));
        }

        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{pk}")
    public ResponseEntity<GroupeData> getGroupe(@PathVariable("pk") Long pk) {
       return ResponseEntity.ok(facade.geGroupe(pk));
    }

    @GetMapping("/new")
    public ResponseEntity<GroupeData> getInstace() {
        return ResponseEntity.ok(new GroupeData());
    }

    @PostMapping
    public ResponseEntity<GroupeData> save(@RequestBody GroupeData grp) throws ModelServiceException, URISyntaxException, IOException {
        facade.save(grp);
       return ResponseEntity.ok(grp);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void  delete(@PathVariable("id") Long id) {
        final GroupeModel grp = modelService.find(GroupeModel.class, id);
        modelService.remove(grp);
    }

    @Override
    protected FlexibleSearch getFlexibleSearch() {
        return flexibleSearch;
    }

    @Override
    protected SettingFacade getSettingFacade() {
        return settingFacade;
    }
}
