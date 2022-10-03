package cm.pak.services;

import cm.pak.models.core.SettingModel;

import java.util.Optional;

public interface SettingService {

    Optional<SettingModel> getSetting();
}
