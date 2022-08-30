package cm.pak.training.populators.core;

import cm.pak.models.core.ExtensionModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.core.ExtensionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExtensionPopulator implements Populator<ExtensionModel, ExtensionData> {

    @Autowired
    private ModelService modelService;

    @Override
    public ExtensionData popule(ExtensionModel source) {
        final  ExtensionData data = new ExtensionData();
        data.setPk(source.getPk());
        data.setCode(source.getCode());
        data.setOwner(source.getOwner());
        data.setCreate(source.getCreate());
        data.setUpdate(source.getUpdate());
        data.setVersion(source.getVersion());
        data.setLongDescription(source.getLongDescription());
        data.setShortDescription(source.getShortDescription());
        data.setInstall(source.isInstall());
        data.setSequence(source.getSequence());
        data.setValue(data.getCode());
        return data;
    }

    @Override
    public ExtensionModel revert(ExtensionData source) {
        final  ExtensionModel data = modelService.find(ExtensionModel.class, source.getPk());
        data.setOwner(source.getOwner());
        data.setCode(source.getCode());
        data.setOwner(source.getOwner());
        data.setUpdate(new Date());
        data.setVersion(source.getVersion());
        data.setLongDescription(source.getLongDescription());
        data.setShortDescription(source.getShortDescription());
        data.setInstall(source.isInstall());
        data.setSequence(source.getSequence());
        return data;
    }
}
