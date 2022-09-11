package cm.pak.training.beans.security;

import cm.pak.annotations.*;
import cm.pak.models.security.GroupeModel;
import cm.pak.training.beans.AbstractData;
import cm.pak.training.beans.core.ExtensionData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", sequence = 1, label = "general.group"),
        @Group(name = "profil", sequence = 2, label = "profil.group")
})
@SearchKey(label = "intitule", value = "code")
public class GroupeData extends AbstractData implements Serializable {
    @Widget(value = "text", group = "general", column = true)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String intitule;
    @Manytoone(updatable = false, group = "general", source = "/api/v1/plugins", column = true)
    private ExtensionData plugin ;
    @Onetomany(group = "profil", editable = false, deletable = false , source = "/api/v1/plugins")
    private Set<AccesRigthData> rigths ;

    public GroupeData() {
        super();
        this.rigths = new HashSet<>();
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

    public Set<AccesRigthData> getRigths() {
        return rigths;
    }

    public void setRigths(Set<AccesRigthData> rigths) {
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
