package cm.pak.training.controllers.core;

import cm.pak.data.MenuData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.facades.core.ExtensionFacade;
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
public class ExtensionControlleur  {
    private static final Logger LOG = LoggerFactory.getLogger(ExtensionControlleur.class);

    @Autowired
    private ExtensionFacade facade ;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<ExtensionData>> getInstalPlugins(final Model model, Authentication auth) throws URISyntaxException, IOException {
        return ResponseEntity.ok(facade.getInstallExtensions(auth.getName())) ;
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

    /**
    @GetMapping(path = "{action}")
    public String modules(final Model model, @PathVariable("action") String action) throws URISyntaxException, IOException {
        init(model, ExtensionData.class,"administration", action, "home/listTemplate.html", "template");
        model.addAttribute("datas", extensionFacade.getExtensions());
        return "/home/template";
    }

    @Override
    protected String title() {
        return "extension.list.form";
    }

    @Override
    protected String prefix() {
        return "extension";
    }

    @Override
    protected String viewPath(String action) {
        if (action.equalsIgnoreCase("all")) {
            return "'/plugins/'+ ${{data.pk}}";
        }
        return "#";
    }
     **/
}
