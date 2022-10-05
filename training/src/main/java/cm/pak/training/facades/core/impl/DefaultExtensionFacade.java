package cm.pak.training.facades.core.impl;

import cm.pak.data.MenuData;
import cm.pak.data.ModuleData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.ExtensionModel;
import cm.pak.models.security.AccesRigth;
import cm.pak.models.security.GroupeModel;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.ExtensionService;
import cm.pak.services.JsonService;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.facades.core.ExtensionFacade;
import cm.pak.training.populators.core.ExtensionPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DefaultExtensionFacade implements ExtensionFacade {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultExtensionFacade.class);
    @Autowired
    private ExtensionService extensionService;
    @Autowired
    private JsonService jsonService ;
    @Autowired
    private ExtensionPopulator populator ;
    @Autowired
    private ModelService modelService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    public ExtensionData getExtension(Long pk, final String username) throws URISyntaxException, IOException {
        final ExtensionData data = populator.populate(extensionService.get(pk));
        data.setMenus(getActions(data, username));
        return data;
    }

    @Override
    public List<ExtensionData> getInstallExtensions(final String username) throws URISyntaxException, IOException {
        final List<ExtensionModel> extensions = extensionService.getInstallExtensions();
        if (!username.equalsIgnoreCase("admin")) {
            return getUserPlugins(username, extensions);
        }
        extensions.sort(new Comparator<ExtensionModel>() {
            @Override
            public int compare(ExtensionModel o1, ExtensionModel o2) {
                return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
            }
        });
        final List<ExtensionData> extensionss = extensions.stream()
                .map(data -> populator.populate(data))
                .collect(Collectors.toList());

        for (ExtensionData ext : extensionss ) {
            ext.setMenus(getActions(ext, username));
        }
        return extensionss;
    }

    @Override
    public List<MenuData> getActions(ExtensionData extension, final String username) throws URISyntaxException, IOException {
       return  getActions(extension.getCode(), username);
    }

    @Override
    public List<MenuData> getActions(String name, final String username) throws URISyntaxException, IOException {
        final ModuleData module = jsonService.getModule(name) ;
        final Map<String, AccesRigth> rigthMatrix = new HashMap<>();
        if (!username.equalsIgnoreCase("admin")) {
            final UserModel user = flexibleSearch.find(UserModel.class, "code", username);
            final Optional<GroupeModel> profil = CollectionUtils.isEmpty(user.getProfils()) ? Optional.empty() : user.getProfils().stream().filter(gr -> gr.getPlugin().getCode().equals(name)).findAny();

            if (!profil.isPresent()) {
                module.setMenus(new HashSet<>());
            } else {
               profil.get().getRigths().forEach(rigth -> rigthMatrix.put(rigth.getName(), rigth));

               for (MenuData menu : module.getMenus()) {
                   cleanMenu(menu, rigthMatrix);
               }
            }
        }
        if (!CollectionUtils.isEmpty(module.getMenus())) {
            module.getMenus().forEach(m -> translate(m));
        }
        return module.getMenus().stream().sorted(new Comparator<MenuData>() {
            @Override
            public int compare(MenuData o1, MenuData o2) {
                return Integer.valueOf(o1.getOrder()).compareTo(Integer.valueOf(o2.getOrder()));
            }
        }) .collect(Collectors.toList());
    }

    private void cleanMenu(MenuData menu, Map<String, AccesRigth> rigthMatrix) {
        if (!CollectionUtils.isEmpty(menu.getChildren())) {
            for (MenuData m : menu.getChildren()) {
                final AccesRigth access = rigthMatrix.get(m.getName());

            }
        }
    }

    private void translate(final MenuData menu) {
        Locale locale = Locale.getDefault();
        menu.setLabel(messageSource.getMessage(menu.getLabel(), null, menu.getLabel(), locale));
        if (!CollectionUtils.isEmpty(menu.getChildren())) {
           menu.getChildren().forEach(m -> translate(m));
        }
        if (!CollectionUtils.isEmpty(menu.getActions())) {
            menu.getActions().forEach(a -> a.setLabel(messageSource.getMessage(a.getLabel(), null, a.getLabel(), locale)));
        }
    }

    @Override
    public List<ExtensionData> getExtensions(final String username) {
        final List<ExtensionModel> datas = extensionService.getExtensions() ;
        if (!username.equalsIgnoreCase("admin")) {
            return getUserPlugins(username, datas);
        }
        return datas.stream()
                .map(ext -> populator.populate(ext))
                .collect(Collectors.toList());
    }

    private List<ExtensionData> getUserPlugins(String username, final List<ExtensionModel> datas) {
        final UserModel user = flexibleSearch.find(UserModel.class, "code", username);
        final Set<String> plugins = !CollectionUtils.isEmpty(user.getProfils()) ? user.getProfils().stream().map(gr -> gr.getPlugin().getCode()).collect(Collectors.toSet()): new HashSet<>();
        return datas.stream()
                .filter(ext -> plugins.contains(ext.getCode()))
                .map(ext -> populator.populate(ext))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(ExtensionData data) throws ModelServiceException {
           final ExtensionModel model = populator.revert(data);
           modelService.createOrUpdate(model);
    }
}
