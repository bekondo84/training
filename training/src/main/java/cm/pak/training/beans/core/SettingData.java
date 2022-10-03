package cm.pak.training.beans.core;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.core.SettingModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group"),
        @Group(name = "email.settings", label = "email.settings.group")
})
public class SettingData extends AbstractItemData implements Serializable {

    @Widget(value = "text", group = "email.settings")
    private String mailHost;
    @Widget(value = "number", group = "email.settings")
    private Integer mailPort ;
    @Widget(value = "email", group = "email.settings")
    private String mailUsername;
    @Widget(value = "password", group = "email.settings")
    private String mailPassword ;
    @Widget(value = "text", group = "email.settings")
    private String mailTransportProtocol;
    @Widget(value = "checkbox", group = "email.settings")
    private boolean mailSmtpAuth;
    @Widget(value = "checkbox", group = "email.settings")
    private boolean mailSmtpStartTtls;
    @Widget(value = "checkbox", group = "email.settings")
    private boolean mailDebug ;
    @Widget(value = "number", group = "general")
    private Integer pageSize ;

    public SettingData() {
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public void setMailPort(Integer mailPort) {
        this.mailPort = mailPort;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailTransportProtocol() {
        return mailTransportProtocol;
    }

    public void setMailTransportProtocol(String mailTransportProtocol) {
        this.mailTransportProtocol = mailTransportProtocol;
    }

    public boolean isMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public void setMailSmtpAuth(boolean mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    public boolean isMailSmtpStartTtls() {
        return mailSmtpStartTtls;
    }

    public void setMailSmtpStartTtls(boolean mailSmtpStartTtls) {
        this.mailSmtpStartTtls = mailSmtpStartTtls;
    }

    public boolean isMailDebug() {
        return mailDebug;
    }

    public void setMailDebug(boolean mailDebug) {
        this.mailDebug = mailDebug;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String getTargetEntity() {
        return SettingModel.class.getName();
    }
}
