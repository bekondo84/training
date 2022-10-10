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
        data.setMailDebug(source.isMailDebug());
        data.setMailHost(source.getMailHost());
        data.setMailPassword(source.getMailPassword());
        data.setMailPort(source.getMailPort());
        data.setMailSmtpAuth(source.isMailSmtpAuth());
        data.setMailSmtpStartTtls(source.isMailSmtpStartTtls());
        data.setMailUsername(source.getMailUsername());
        data.setMailTransportProtocol(source.getMailTransportProtocol());
        data.setPageSize(source.getPageSize());
        data.setPoolName(source.getPoolName());
        data.setPoolSize(source.getPoolSize());
        return data;
    }

    @Override
    public SettingModel revert(SettingData source) throws ParseException {
        final SettingModel data = new SettingModel();
        revert(source, data);
        data.setMailDebug(source.isMailDebug());
        data.setMailHost(source.getMailHost());
        data.setMailPassword(source.getMailPassword());
        data.setMailPort(source.getMailPort());
        data.setMailSmtpAuth(source.isMailSmtpAuth());
        data.setMailSmtpStartTtls(source.isMailSmtpStartTtls());
        data.setMailUsername(source.getMailUsername());
        data.setMailTransportProtocol(source.getMailTransportProtocol());
        data.setPageSize(source.getPageSize());
        data.setPoolName(source.getPoolName());
        data.setPoolSize(source.getPoolSize());
        return data;
    }
}
