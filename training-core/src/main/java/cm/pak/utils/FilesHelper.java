package cm.pak.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FilesHelper {

    private static final Logger LOG = LoggerFactory.getLogger(FilesHelper.class);
    public static final String JSON_EXTENSION = ".json";
    public static final String MODULES_DIR = "modules";

    /**
     * Get the module json files which contains all the available modules for the system
     * @return
     * @throws URISyntaxException
     */
    public static synchronized File getModulesList() throws URISyntaxException {
        final URL url = FilesHelper.class
                        .getClassLoader()
                        .getResource("module.json");

        return new File(url.toURI()) ;
    }

    public static synchronized File getModule(final String name) throws URISyntaxException {
         final URL url = FilesHelper.class
                .getClassLoader()
                .getResource(MODULES_DIR
                                .concat(File.separator)
                                .concat(name.concat(JSON_EXTENSION)));
        return new File(url.toURI()) ;
    }
}
