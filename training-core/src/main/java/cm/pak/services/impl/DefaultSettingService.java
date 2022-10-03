package cm.pak.services.impl;

import cm.pak.models.core.SettingModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultSettingService implements SettingService {
    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    public Optional<SettingModel> getSetting() {
        List<SettingModel> settings = flexibleSearch.find("SELECT c FROM SettingModel c");
        if (!CollectionUtils.isEmpty(settings)) {
            return Optional.of(settings.get(0));
        }
        return Optional.empty();
    }
}
