package cm.pak.training.beans.core;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.core.SettingModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group"),
        @Group(name = "email.settings", label = "email.settings.group")
})
public class SettingData extends AbstractItemData implements Serializable {

    @Widget(value = "text", group = "email.settings")
    private String smtp ;
    @Widget(value = "email", group = "email.settings")
    private String email ;
    @Widget(value = "number", group = "email.settings")
    private Integer emailRetry ;
    @Widget(value = "email", group = "email.settings")
    private String notifEmail;

    public SettingData() {
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmailRetry() {
        return emailRetry;
    }

    public void setEmailRetry(Integer emailRetry) {
        this.emailRetry = emailRetry;
    }

    public String getNotifEmail() {
        return notifEmail;
    }

    public void setNotifEmail(String notifEmail) {
        this.notifEmail = notifEmail;
    }

    @Override
    public String getTargetEntity() {
        return SettingModel.class.getName();
    }
}
