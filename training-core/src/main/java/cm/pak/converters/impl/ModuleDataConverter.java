package cm.pak.converters.impl;

import cm.pak.converters.Converter;
import cm.pak.data.ModuleData;
import cm.pak.models.core.ExtensionModel;
import org.springframework.stereotype.Component;

@Component
public class ModuleDataConverter implements Converter<ModuleData, ExtensionModel> {
    @Override
    public ExtensionModel convert(ModuleData source) {
        final ExtensionModel ext = new ExtensionModel();
        ext.setCode(source.getName());
        ext.setVersion(source.getVersion());
        ext.setShortDescription(source.getDescription());
        ext.setLongDescription(source.getLongDescription());
        ext.setOwner(source.getOwner());
        ext.setSequence(source.getSequence());
        ext.setDepends(source.getDepends());
        return ext;
    }
}
