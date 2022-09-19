package cm.pak.training.populators.core;

import cm.pak.models.core.SettingModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.core.SettingData;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class SettingPopulator implements Populator<SettingModel, SettingData> {

    @Override
    public SettingData populate(SettingModel source) {
        final SettingData data = new SettingData();
        populate(source, data);
        data.setEmail(source.getEmail());
        data.setEmailRetry(source.getEmailRetry());
        data.setNotifEmail(source.getNotifEmail());
        data.setSmtp(source.getSmtp());
        return data;
    }

    @Override
    public SettingModel revert(SettingData source) throws ParseException {
        final SettingModel data = new SettingModel();
        revert(source, data);
        data.setEmail(source.getEmail());
        data.setEmailRetry(source.getEmailRetry());
        data.setNotifEmail(source.getNotifEmail());
        data.setSmtp(source.getSmtp());
        return data;
    }
}
