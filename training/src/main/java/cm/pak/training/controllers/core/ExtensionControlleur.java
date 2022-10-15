package cm.pak.training.controllers.core;

import cm.pak.data.MenuData;
import cm.pak.data.PaginationData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.facades.core.ExtensionFacade;
import cm.pak.training.facades.core.SettingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plugins")
@CrossOrigin
public class ExtensionControlleur  extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(ExtensionControlleur.class);

    @Autowired
    private ExtensionFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<PaginationData<ExtensionData>> getInstalPlugins(final Model model, Authentication auth) throws URISyntaxException, IOException {
        final PaginationData<ExtensionData> result = new PaginationData<>();
        result.setCurrentPage(1);
        result.setTotalPages(1);
        result.setItems(facade.getInstallExtensions(auth.getName()));
        return ResponseEntity.ok(result) ;
    }

    @GetMapping(value = "/{pk}")
    public ResponseEntity<ExtensionData> getPlugin(@PathVariable("pk") Long pk, final Authentication auth) throws URISyntaxException, IOException {
         return  ResponseEntity.ok(facade.getExtension(pk, auth.getName()));
    }
    @GetMapping("/menus/{name}")
    @CrossOrigin
    public ResponseEntity<List<MenuData>> getMenus(final Model model, @PathVariable("name") String name, final Authentication auth) throws URISyntaxException, IOException {
        return ResponseEntity.ok(facade.getActions(name, auth.getName())) ;
    }

    @PostMapping
    public ResponseEntity<ExtensionData> save(@RequestBody ExtensionData data) throws ModelServiceException {
        facade.save(data);
        return ResponseEntity.ok(data);
    }

    @Override
    protected FlexibleSearch getFlexibleSearch() {
        return flexibleSearch;
    }

    @Override
    protected SettingFacade getSettingFacade() {
        return null;
    }
}
