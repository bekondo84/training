package cm.pak.models.core;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_sett")
public class SettingModel extends ItemModel implements Serializable {
    @Column(name = "t_smtp")
    private String smtp ;
    @Column(name = "t_syma")
    private String email ;
    @Column(name = "t_emre")
    private Integer emailRetry ;
    @Column(name = "t_noem")
    private String notifEmail;

    public SettingModel() {
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
}
