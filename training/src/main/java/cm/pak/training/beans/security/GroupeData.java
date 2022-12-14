package cm.pak.training.beans.security;

import cm.pak.annotations.*;
import cm.pak.models.security.GroupeModel;
import cm.pak.training.beans.AbstractItemData;
import cm.pak.training.beans.core.ExtensionData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", sequence = 1, label = "general.group"),
        @Group(name = "profil", sequence = 2, label = "profil.group")
})
@SearchKeys({
        @SearchKey(label = "code", value = "code", primary = true),
        @SearchKey(label = "intitule", value = "intitule"),
        @SearchKey(label = "Plugin", value = "plugin")
})
public class GroupeData extends AbstractItemData implements Serializable {
    @NotNull
    @Widget(value = "text", group = "general", column = true)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String intitule;
    @NotNull
    @Manytoone(updatable = false, group = "general", source = "/api/v1/plugins", column = true)
    private ExtensionData plugin ;
    @NotNull
    @Onetomany(group = "profil", editable = false, deletable = false , source = "/api/v1/plugins")
    private List<AccesRigthData> rigths ;

    public GroupeData() {
        super();
        this.rigths = new ArrayList<>();
    }

    @Override
    public String getTargetEntity() {
        return GroupeModel.class.getName();
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

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public ExtensionData getPlugin() {
        return plugin;
    }

    public void setPlugin(ExtensionData plugin) {
        this.plugin = plugin;
    }

    public List<AccesRigthData> getRigths() {
        return rigths;
    }

    public void setRigths(List<AccesRigthData> rigths) {
        this.rigths = rigths;
    }

    public void addRigth(final AccesRigthData rigth) {
        rigths.add(rigth);
    }
    @Override
    public String toString() {
        return "GroupeData{" +
                "pk=" + pk +
                ", create=" + create +
                ", update=" + update +
                ", code='" + code + '\'' +
                ", intitule='" + intitule + '\'' +
                ", plugin=" + plugin +
                ", rigths=" + rigths +
                '}';
    }
}
