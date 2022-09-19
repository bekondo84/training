package cm.pak.training.facades.core.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.core.SettingModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.core.SettingData;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.populators.core.SettingPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Component
public class DefaultSettingFacade implements SettingFacade {

    public static final String ALL_SETTING = "SELECT c FROM SettingModel AS c";
    @Autowired
    private SettingPopulator populator;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private ModelService modelService ;

    @Override
    public SettingData getSetting() {
        final List<SettingModel> settings = flexibleSearch.find(ALL_SETTING);
        if (!CollectionUtils.isEmpty(settings)) {
            return populator.populate(settings.get(0));
        }
        return new SettingData();
    }

    @Transactional
    @Override
    public SettingData save(SettingData source) throws ParseException, ModelServiceException {
        final SettingModel setting = populator.revert(source);
        modelService.createOrUpdate(setting);

        return getSetting();
    }
}
