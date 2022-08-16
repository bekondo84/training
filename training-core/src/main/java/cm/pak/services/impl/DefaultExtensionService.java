package cm.pak.services.impl;

import cm.pak.converters.impl.MenuDataConverter;
import cm.pak.converters.impl.ModuleDataConverter;
import cm.pak.data.MenuData;
import cm.pak.data.ModuleData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.AbstractMenu;
import cm.pak.models.core.ExtensionModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.ExtensionService;
import cm.pak.services.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class DefaultExtensionService implements ExtensionService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultExtensionService.class);
    private static final String QUERY = "SELECT c FROM ExtensionModel AS c JOIN FETCH c.menus WHERE c.pk = %s";

    @Autowired
    private ModuleDataConverter moduleConverter ;
    @Autowired
    private MenuDataConverter menuConverter;
    @Autowired
    private ModelService modelService ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private JsonService jsonService;

    @Override
    public List<ExtensionModel> getInstallExtensions() {
        final String QUERY = "SELECT c FROM ExtensionModel AS c WHERE install = %s ORDER BY sequence ASC";
        List<ExtensionModel> extensions = flexibleSearch.find(String.format(QUERY, Boolean.TRUE)) ;
        return extensions;
    }

    @Override
    public List<ExtensionModel> getExtensions() {
        return flexibleSearch.find("SELECT c FROM ExtensionModel AS c");
    }

    @Override
    @Transactional
    public ExtensionModel load(final String source) throws ModelServiceException, URISyntaxException, IOException {
        final String QUERY = "SELECT c FROM ExtensionModel AS c WHERE c.code = '%s'" ;
        final ModuleData moduleData = jsonService.getModule(source) ;
        final List<ExtensionModel> extensions = flexibleSearch.find(String.format(QUERY, moduleData.getName())) ;
        final ExtensionModel extension = moduleConverter.convert(moduleData);

        if (!CollectionUtils.isEmpty(extensions)) {
            extension.setPk(extensions.get(0).getPk());
        }
        modelService.createOrUpdate(extension);

        return flexibleSearch.find(ExtensionModel.class, "code", extension.getCode());
    }

    @Override
    @Transactional
    public ExtensionModel install(final ExtensionModel source) throws URISyntaxException, IOException, ModelServiceException {
        final ModuleData module = jsonService.getModule(source.getCode());
        source.setInstall(Boolean.TRUE);
        modelService.createOrUpdate(source);
        return flexibleSearch.find(ExtensionModel.class, source.getPk());
    }

    @Override
    @Transactional
    public ExtensionModel uninstall(final ExtensionModel source) throws ModelServiceException {
        source.setInstall(Boolean.FALSE);
        modelService.createOrUpdate(source);
        return source;
    }

    @Override
    public ExtensionModel get(Long pk) throws URISyntaxException, IOException {
        final ExtensionModel extension = modelService.find(ExtensionModel.class, pk);
        final ModuleData module = jsonService.getModule(extension.getCode());
        final Set<AbstractMenu> menus = new HashSet<>();

        if (Objects.nonNull(module)
                && !CollectionUtils.isEmpty(module.getMenus())) {

            for (MenuData menu : module.getMenus()) {
                if (Objects.nonNull(menu)) {
                    menus.add(menuConverter.convert(menu));
                }
            }
        }
        extension.setMenus(menus);
        return extension;
    }
}
