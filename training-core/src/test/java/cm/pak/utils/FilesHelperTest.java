package cm.pak.utils;

import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class FilesHelperTest {

    public static final String MODULE_NAME = "administration";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getModulesList() throws URISyntaxException {
        assertNotNull(FilesHelper.getModulesList());
        assertTrue(FilesHelper.getModulesList().isFile());
        assertEquals(FilesHelper.getModulesList().getName(), "module.json");
    }

    @Test
    public void getModule() throws URISyntaxException {
        assertNotNull(FilesHelper.getModule(MODULE_NAME));
        assertTrue(FilesHelper.getModule(MODULE_NAME).isFile());
        assertEquals(FilesHelper.getModule(MODULE_NAME).getName(), "administration.json");
    }
}