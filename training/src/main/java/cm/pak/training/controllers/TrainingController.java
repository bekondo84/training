package cm.pak.training.controllers;

import cm.pak.data.MetaData;
import cm.pak.services.MetaService;
import cm.pak.training.facades.core.ExtensionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping
public class TrainingController extends AbstractController{
    private static final Logger LOG = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private ExtensionFacade extensionFacade;

    @Autowired
    private MetaService metaService ;

    @GetMapping
     public String homePage(final Model model) throws URISyntaxException, IOException {
        init(model, null, null, null, null, null);
        return "/home/template";
     }

     @ResponseBody
     @GetMapping("/api/v1/meta/{name}")
     public ResponseEntity<MetaData> metaData(@PathVariable("name") String name) throws ClassNotFoundException {
         LOG.info(String.format("Meta : %s ", metaService.getMeta(name)));
         return ResponseEntity.ok(metaService.getMeta(name));
     }

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected String prefix() {
        return null;
    }
}
