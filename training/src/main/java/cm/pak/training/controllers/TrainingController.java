package cm.pak.training.controllers;

import cm.pak.models.core.ExtensionModel;
import cm.pak.services.ExtensionService;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.facades.core.ExtensionFacade;
import cm.pak.training.populators.core.ExtensionPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping
public class TrainingController extends AbstractController{
    @Autowired
    private ExtensionFacade extensionFacade;

    @GetMapping
     public String homePage(final Model model) throws URISyntaxException, IOException {
        init(model, null, null, null, null, null);
        return "/home/template";
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
