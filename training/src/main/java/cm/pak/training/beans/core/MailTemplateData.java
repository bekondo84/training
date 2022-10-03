package cm.pak.training.beans.core;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.MailTemplateModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group"),
        @Group(name = "template", label = "template.group", sequence = 2)
})
public class MailTemplateData extends AbstractItemData implements Serializable {
    @Widget(value = "text", group = "general", column = true)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String intitule ;
    @Widget(value = "textarea", group = "template", column = true)
    private String text ;

    public MailTemplateData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getTargetEntity() {
        return MailTemplateModel.class.getName();
    }
}
