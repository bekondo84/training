package cm.pak.training.beans.security;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;

import java.io.Serializable;

@Groups({
        @Group(name = "general", label = "general.group")
})
public class SetPasswordData implements Serializable {
    private Long pk ;
    @Widget(value = "password", group = "general", column = true)
    private String newPassword;
    @Widget(value = "password", group = "general", column = true)
    private String confirmPasswword;

    public SetPasswordData() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPasswword() {
        return confirmPasswword;
    }

    public void setConfirmPasswword(String confirmPasswword) {
        this.confirmPasswword = confirmPasswword;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return "ChangePasswordData{" +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPasswword='" + confirmPasswword + '\'' +
                '}';
    }
}
