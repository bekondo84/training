package cm.pak.services.impl;

import cm.pak.data.ActionData;
import cm.pak.data.MenuData;
import cm.pak.data.ModuleData;
import cm.pak.data.ModuleListData;
import cm.pak.services.JsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class DefaultJsonServiceTest {

    private static Logger LOG = LoggerFactory.getLogger(DefaultJsonServiceTest.class);

    private JsonService jsonService;

    @Before
    public void setUp() throws Exception {
        jsonService = new DefaultJsonService(new ObjectMapper());
    }

    @Test
    public void getModuleList() throws URISyntaxException, IOException {
        final ModuleListData data = jsonService.getModuleList();
        assertNotNull(data);
        assertTrue(!CollectionUtils.isEmpty(data.getModules()));
        assertEquals(data.getModules().size(), 2);
    }

    @Test
    public void getModule() throws URISyntaxException, IOException {
        final ModuleData module = jsonService.getModule("administration");
        assertNotNull(module);
        assertEquals("1.0", module.getVersion());
        assertEquals("administration", module.getName());
        assertEquals("Modules administration de l'application", module.getDescription());
        assertEquals("...", module.getLongDescription());
        assertEquals("Port Autonome de Kribi", module.getOwner());
        assertEquals((Integer)100, module.getSequence());
        assertTrue(CollectionUtils.isEmpty(module.getDepends()));
        assertEquals(3, module.getMenus().size());
        final MenuData menuData = module.getMenus().stream()
                .filter(m ->m.getName().equalsIgnoreCase("modules"))
                        .findAny().get();
        final MenuData menuData2 = menuData.getChildren().stream()
                .filter(m -> m.getName().equalsIgnoreCase("load.extensions"))
                        .findAny().get();
        assertEquals(2, menuData2.getActions().size());
        assertEquals("load", menuData2.getLabel());
        assertTrue(module.isAutoInstall());
    }
}