package cm.pak.services;

import cm.pak.data.ModuleData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.ExtensionModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public interface ExtensionService {

    List<ExtensionModel> getInstallExtensions() ;

    List<ExtensionModel> getExtensions() ;
    /**
     *
     * @param name
     * @return
     */
    ExtensionModel load(final String name) throws ModelServiceException, URISyntaxException, IOException;

    /**
     *
     * @param names
     * @throws ModelServiceException
     * @throws URISyntaxException
     * @throws IOException
     */
   default void load(final String ... names) throws ModelServiceException, URISyntaxException, IOException {

       for (String name : names) {

           if (Objects.nonNull(name)) {
               load(name);
           }
       }
   }
    /**
     *
     * @param source
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ModelServiceException
     */
    ExtensionModel install (final ExtensionModel source) throws URISyntaxException, IOException, ModelServiceException;

    /**
     *
     * @param source
     * @return
     * @throws ModelServiceException
     */
    ExtensionModel uninstall(final ExtensionModel source) throws ModelServiceException;

    /**
     *
     * @param pk
     * @return
     */
    ExtensionModel get(final Long pk) throws URISyntaxException, IOException;

}
