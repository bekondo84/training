package cm.pak.training.beans.security;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;

import java.io.Serializable;

@Groups({
        @Group(name = "general", label = "general.group")
})
public class ChangePasswordData implements Serializable {
    @Widget(value = "password", group = "general", column = true)
    private String oldPassword ;
    @Widget(value = "password", group = "general", column = true)
    private String newPassword;
    @Widget(value = "password", group = "general", column = true)
    private String confirmPasswword;

    public ChangePasswordData() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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

    @Override
    public String toString() {
        return "ChangePasswordData{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPasswword='" + confirmPasswword + '\'' +
                '}';
    }
}
