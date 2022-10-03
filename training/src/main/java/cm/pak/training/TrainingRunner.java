package cm.pak.training;

import cm.pak.converters.impl.ModuleDataConverter;
import cm.pak.data.ModuleData;
import cm.pak.data.ModuleListData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.ExtensionModel;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.ExtensionService;
import cm.pak.services.JsonService;
import cm.pak.training.facades.security.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@Component
public class TrainingRunner implements ApplicationRunner {

    public static final String GET_EXT_BY_NAME_QUERY = "SELECT c FROM ExtensionModel AS c WHERE c.code = %s";
    @Autowired
    private JsonService jsonService ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private ExtensionService extensionService;
    @Autowired
    private ModuleDataConverter moduleDataConverter;
    @Autowired
    private UserFacade userFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializeApplication();
        userFacade.createAdminUser();
    }

    private void initializeApplication() throws URISyntaxException, IOException, ModelServiceException {
        final ModuleListData moduleList = jsonService.getModuleList() ;

        if (Objects.nonNull(moduleList) && !CollectionUtils.isEmpty(moduleList.getModules())) {

            for (final String name : moduleList.getModules()) {

                if (StringUtils.hasLength(name)) {
                    final ModuleData module = jsonService.getModule(name);
                    final ExtensionModel extension = extensionService.load(name);
                    if (module.isAutoInstall()) {
                        extensionService.install(extension);
                    }
                }
            }
        }
    }
}
