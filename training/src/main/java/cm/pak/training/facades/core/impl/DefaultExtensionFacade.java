package cm.pak.training.facades.core.impl;

import cm.pak.data.MenuData;
import cm.pak.data.ModuleData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.ExtensionModel;
import cm.pak.repositories.ModelService;
import cm.pak.services.ExtensionService;
import cm.pak.services.JsonService;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.facades.core.ExtensionFacade;
import cm.pak.training.populators.core.ExtensionPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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

    @Override
    public ExtensionData getExtension(Long pk) throws URISyntaxException, IOException {
        final ExtensionData data = populator.popule(extensionService.get(pk));
        data.setMenus(getActions(data));
        LOG.info(String.format("*******************  Data : %s", data));
        return data;
    }

    @Override
    public List<ExtensionData> getInstallExtensions() throws URISyntaxException, IOException {
        final List<ExtensionModel> extensions = extensionService.getInstallExtensions();
        extensions.sort(new Comparator<ExtensionModel>() {
            @Override
            public int compare(ExtensionModel o1, ExtensionModel o2) {
                return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
            }
        });
        final List<ExtensionData> extensionss = extensions.stream()
                .map(data -> populator.popule(data))
                .collect(Collectors.toList());

        for (ExtensionData ext : extensionss ) {
            ext.setMenus(getActions(ext));
        }
        return extensionss;
    }

    @Override
    public List<MenuData> getActions(ExtensionData extension) throws URISyntaxException, IOException {
       return  getActions(extension.getCode());
    }

    @Override
    public List<MenuData> getActions(String name) throws URISyntaxException, IOException {
        final ModuleData module = jsonService.getModule(name) ;
        return module.getMenus().stream().sorted(new Comparator<MenuData>() {
            @Override
            public int compare(MenuData o1, MenuData o2) {
                return Integer.valueOf(o1.getOrder()).compareTo(Integer.valueOf(o2.getOrder()));
            }
        }) .collect(Collectors.toList());
    }

    @Override
    public List<ExtensionData> getExtensions() {
        return extensionService.getExtensions().stream()
                .map(ext -> populator.popule(ext))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(ExtensionData data) throws ModelServiceException {
           final ExtensionModel model = populator.revert(data);
           modelService.createOrUpdate(model);
    }
}
