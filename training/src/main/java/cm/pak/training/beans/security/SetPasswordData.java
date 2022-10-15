package cm.pak.training.beans.security;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group")
})
public class SetPasswordData implements Serializable {
    private Long itempk;
    @NotNull
    @Widget(value = "password", group = "general", column = true)
    private String newPassword;
    @NotNull
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

    public Long getItempk() {
        return itempk;
    }

    public void setItempk(Long itempk) {
        this.itempk = itempk;
    }

    @Override
    public String toString() {
        return "ChangePasswordData{" +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPasswword='" + confirmPasswword + '\'' +
                '}';
    }
}
