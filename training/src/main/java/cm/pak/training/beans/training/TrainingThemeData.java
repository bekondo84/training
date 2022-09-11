package cm.pak.training.beans.training;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.SearchKey;
import cm.pak.annotations.Widget;
import cm.pak.models.training.TrainingModel;
import cm.pak.training.beans.AbstractData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 2)
})
@SearchKey(value = "code", label = "code")
public class TrainingThemeData extends AbstractData {
    @Widget(value = "text", group = "general", column = true)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String name ;
    @Widget(value = "text", group = "general", column = true)
    private String description;

    public TrainingThemeData() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTargetEntity() {
        return TrainingModel.class.getName();
    }
}
