package cm.pak.models.core;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_sett")
public class SettingModel extends ItemModel implements Serializable {
    @Column(name = "t_maho")
    private String mailHost;
    @Column(name = "t_mapo")
    private Integer mailPort ;
    @Column(name = "t_maun")
    private String mailUsername;
    @Column(name = "t_mapwd")
    private String mailPassword ;
    @Column(name = "t_matrpr")
    private String mailTransportProtocol;
    @Column(name = "t_masmau")
    private boolean mailSmtpAuth;
    @Column(name = "t_masmls")
    private boolean mailSmtpStartTtls;
    @Column(name = "t_made")
    private boolean mailDebug ;
    @Column(name = "t_pasi")
    private Integer pageSize ;
    @Column(name = "t_posi")
    private Integer poolSize ;
    @Column(name = "t_pona")
    private String poolName ;

    public SettingModel() {
        mailDebug = true;
        mailSmtpStartTtls = true;
        mailSmtpAuth = true;
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

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }
}
