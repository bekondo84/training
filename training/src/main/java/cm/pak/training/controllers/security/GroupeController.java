package cm.pak.training.controllers.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.GroupeModel;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.security.GroupeData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.security.GroupeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupeController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(GroupeController.class);

    @Autowired
    private GroupeFacade facade;
    @Autowired
    protected ModelService modelService;

    @GetMapping
    public ResponseEntity<List<GroupeData>> getGroupes() {
        return ResponseEntity.ok(facade.getGroupes());
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
}
