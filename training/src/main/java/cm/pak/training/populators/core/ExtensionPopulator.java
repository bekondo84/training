package cm.pak.training.populators.core;

import cm.pak.models.core.ExtensionModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.core.ExtensionData;
import org.springframework.stereotype.Component;

@Component
public class ExtensionPopulator implements Populator<ExtensionModel, ExtensionData> {

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
        return data;
    }

    @Override
    public ExtensionModel revert(ExtensionData source) {
        return null;
    }
}
