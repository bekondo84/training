package cm.pak.training.beans.security;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;

import java.io.Serializable;

@Groups({
        @Group(name = "general", label = "general")
})
public class ResetPasswordData implements Serializable {
    @Widget(value = "email", group = "general", nullable = false)
    private String username;

    public ResetPasswordData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
