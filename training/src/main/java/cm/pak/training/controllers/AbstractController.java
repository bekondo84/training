package cm.pak.training.controllers;

import cm.pak.data.ActionData;
import cm.pak.data.MenuData;
import cm.pak.services.MetaService;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.facades.core.ExtensionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractController {

    @Autowired
    protected ExtensionFacade extensionFacade ;
    @Autowired
    protected MetaService metaService;

    protected void init(final Model model, Class clazz, final String plugin, final String menu, final String template, final String fragment) throws URISyntaxException, IOException {
        final List<ExtensionData> extensions = extensionFacade.getInstallExtensions();
        model.addAttribute("plugins", extensions);
        ExtensionData pluginD = extensions.get(0);

        if (Objects.nonNull(plugin)) {
            pluginD = extensions
                    .stream().filter(plug -> plug.getCode().equalsIgnoreCase(plugin))
                    .findAny().get();
        }
        final List<MenuData> menus = extensionFacade.getActions(pluginD) ;
        model.addAttribute("menus", menus);
        model.addAttribute("plugin", plugin);
        model.addAttribute("menu", menu);
        model.addAttribute("template", template);
        model.addAttribute("fragment", fragment);
        model.addAttribute("prefix", prefix());
        model.addAttribute("title", title());
        model.addAttribute("actions",extensionFacade.getAction(menu, menus));
        model.addAttribute("view_url", viewPath(menu));
        model.addAttribute("meta", Objects.nonNull(clazz) ? metaService.getMeta(clazz) : null);
        model.addAttribute("columns", Objects.nonNull(clazz) ? metaService.getColumns(clazz) : null);
    }

    protected abstract String title() ;
    protected abstract String prefix() ;
    protected String viewPath(final String action) { return "#" ;}

}
