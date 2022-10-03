package cm.pak.training.beans.core;

import cm.pak.annotations.GlobalConfig;
import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.core.MailModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalConfig(updatable = false, creatable = false)
@Groups({
        @Group(name = "general", label = "general.group")
})
public class MailData extends AbstractItemData implements Serializable {
    @Widget(value = "email", group = "general", column = true)
    private String to ;
    @Widget(value = "text", group = "general", column = true)
    private String subject ;
    @Widget(value = "textarea", group = "general", column = true)
    private String text;

    public MailData() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getTargetEntity() {
        return MailModel.class.getName();
    }
}
