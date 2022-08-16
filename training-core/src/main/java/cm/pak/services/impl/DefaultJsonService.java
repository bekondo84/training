package cm.pak.services.impl;

import cm.pak.data.ModuleData;
import cm.pak.data.ModuleListData;
import cm.pak.services.JsonService;
import cm.pak.utils.FilesHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class DefaultJsonService implements JsonService {

    @Autowired
    private ObjectMapper mapper ;

    public DefaultJsonService() {
    }

    public DefaultJsonService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ModuleListData getModuleList() throws URISyntaxException, IOException {
        final ModuleListData data = mapper.readValue(FilesHelper.getModulesList(), ModuleListData.class);
        return data;
    }

    @Override
    public ModuleData getModule(String name) throws URISyntaxException, IOException {
        final ModuleData data = mapper.readValue(FilesHelper.getModule(name), ModuleData.class);
        return data;
    }


}
