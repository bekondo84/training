package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.TrainingThemeModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group", sequence = 2),
        @Group(name = "description", label = "description.label", sequence = 3)
})
@SearchKey(value = "name", label = "name")
public class TrainingData extends AbstractItemData implements Serializable {
    @Widget(value = "text", group = "general", column = true)
    private String name ;
    @Manytoone(group = "general", source = "/api/v1/themes", column = true)
    private TrainingThemeData theme ;
    @Widget(value = "text", group = "general", column = true)
    private String description;
    @Widget(value = "textarea", group = "description")
    private String fullDescription;
    @Widget(value = "number", group = "general", column = true)
    private Integer sequence;
    @Widget(value = "file", group = "general", column = true)
    private String image ;
    @Widget(value = "checkbox", group = "general", editable = false, column = true)
    private boolean activate ;

    public TrainingData() {
        activate = false;
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

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public TrainingThemeData getTheme() {
        return theme;
    }

    public void setTheme(TrainingThemeData theme) {
        this.theme = theme;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    @Override
    public String getTargetEntity() {
        return TrainingThemeModel.class.getName();
    }
}
