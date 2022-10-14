package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.TrainingModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 2)
})
@SearchKeys({
        @SearchKey(value = "code", label = "code", primary = true),
        @SearchKey(value = "name", label = "name"),
        @SearchKey(value = "description", label = "description")
})
public class TrainingThemeData extends AbstractItemData {
    @NotNull
    @Widget(value = "text", group = "general", column = true)
    private String code ;
    @NotNull
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
