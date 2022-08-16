package cm.pak.services;

import cm.pak.data.ModuleData;
import cm.pak.data.ModuleListData;

import java.io.IOException;
import java.net.URISyntaxException;

public interface JsonService {

      /**
       *
       * @return
       * @throws URISyntaxException
       * @throws IOException
       */
      ModuleListData getModuleList() throws URISyntaxException, IOException;

      /**
       *
       * @param name
       * @return
       */
      ModuleData getModule(final String name) throws URISyntaxException, IOException;
}
