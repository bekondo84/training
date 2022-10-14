package cm.pak.training.beans.training;

import cm.pak.annotations.*;
import cm.pak.models.training.ClassRoomModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@SearchKeys({
        @SearchKey(value = "code", label = "code", primary = true),
        @SearchKey(value = "intitule", label = "intitule"),
        @SearchKey(value = "adresse", label = "adresse"),
        @SearchKey(value = "localisation", label = "localisation")
})
@Groups({
        @Group(name = "general", label = "general.group", sequence = 2)
})
public class ClassRoomData extends AbstractItemData implements Serializable {
    @NotNull
    @Widget(value = "text", group = "general", updatable = true, column = true)
    private String code;
    @Widget(value = "text", group = "general", column = true)
    private String intitule;
    @NotNull
    @Widget(value = "number", group = "general", column = true)
    private Integer ability;
    @NotNull
    @Widget(value = "text", group = "general", column = true)
    private String adresse;
    @NotNull
    @Widget(value = "text", group = "general", column = true)
    private String localisation;

    public ClassRoomData() {
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

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    @Override
    public String getTargetEntity() {
        return ClassRoomModel.class.getName();
    }
}
