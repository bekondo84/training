package cm.pak.training.populators.core;

import cm.pak.models.core.ExtensionModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.core.ExtensionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

@Component
public class ExtensionPopulator implements Populator<ExtensionModel, ExtensionData> {

    @Autowired
    private ModelService modelService;
    @Autowired
    private MessageSource messageSource;

    @Override
    public ExtensionData populate(ExtensionModel source) {
        final  ExtensionData data = new ExtensionData();
        populate(source, data);
        data.setCode(source.getCode());
        data.setName(messageSource.getMessage(source.getCode(), null, Locale.getDefault()));
        data.setOwner(source.getOwner());
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
        revert(source, data);
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
