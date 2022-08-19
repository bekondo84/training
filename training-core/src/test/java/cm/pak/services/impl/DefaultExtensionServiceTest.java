package cm.pak.services.impl;

import cm.pak.TrainingCoreConfig;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.ExtensionModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.ExtensionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrainingCoreConfig.class)
public class DefaultExtensionServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultExtensionServiceTest.class);
    public static final String ADMINISTRATION = "administration";
    @Autowired
    private ExtensionService extensionService ;
    private ExtensionModel module = null;
    @Autowired
    private FlexibleSearch flexibleSearch ;

    @Before
    public void setUp() throws Exception {
        module = flexibleSearch.find(ExtensionModel.class, 1L);

        if (Objects.isNull(module)) {
            module = extensionService.load(ADMINISTRATION);
        }

    }

    @Test
    public void createAdminExtension() throws ModelServiceException, URISyntaxException, IOException {
        final String extName = ADMINISTRATION;

        assertNotNull(module);
        assertEquals("1.0", module.getVersion());
        assertEquals("administration", module.getCode());
        assertEquals("Modules administration de l'application", module.getShortDescription());
        assertEquals("...", module.getLongDescription());
        assertEquals("Port Autonome de Kribi", module.getOwner());
        assertTrue(CollectionUtils.isEmpty(module.getDepends()));
    }

    @Test
    public void installModule() throws ModelServiceException, URISyntaxException, IOException {
         final ExtensionModel arg = flexibleSearch.find(ExtensionModel.class, 1L);
        LOG.info("***********************************************"+module);
        ExtensionModel module = extensionService.install(arg);
        assertNotNull(module);
        assertEquals("1.0", module.getVersion());
        assertEquals("administration", module.getCode());
        assertEquals("Modules administration de l'application", module.getShortDescription());
        assertEquals("...", module.getLongDescription());
        assertEquals("Port Autonome de Kribi", module.getOwner());
        assertTrue(CollectionUtils.isEmpty(module.getDepends()));
        assertEquals(true, module.isInstall());

    }

    @Test
    public void uninstallModule() throws ModelServiceException {
        module = extensionService.uninstall(module);
        assertNotNull(module);
        assertEquals(false, module.isInstall());
    }

    @Test
    public void get() throws URISyntaxException, IOException {
        module = extensionService.get(module.getPk());
        assertNotNull(module);
    }
}
