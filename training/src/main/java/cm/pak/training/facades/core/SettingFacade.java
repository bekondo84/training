package cm.pak.training.facades.core;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.core.SettingData;

import java.text.ParseException;

public interface SettingFacade {

    SettingData getSetting() ;
    SettingData save(final SettingData source) throws ParseException, ModelServiceException;
}
