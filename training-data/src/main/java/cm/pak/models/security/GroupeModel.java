package cm.pak.models.security;

import cm.pak.models.core.ExtensionModel;
import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_group")
public class GroupeModel extends ItemModel implements Serializable {

    @Column(name = "t_code", unique = true, nullable = false)
    private String code ;
    @Column(name = "t_inti")
    private String intitule;
    @ManyToOne
    private ExtensionModel plugin ;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "t_ac")
    private Set<AccesRigth> rigths ;

    public GroupeModel() {
        super();
        rigths = new HashSet<>();
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

    public ExtensionModel getPlugin() {
        return plugin;
    }

    public void setPlugin(ExtensionModel plugin) {
        this.plugin = plugin;
    }

    public Set<AccesRigth> getRigths() {
        return rigths;
    }

    public void setRigths(Set<AccesRigth> rigths) {
        this.rigths = rigths;
    }

    public void addAccesRigth(AccesRigth rigth) {
        if (rigths == null ) {
            this.rigths = new HashSet<>();
        }
        this.rigths.add(rigth);
    }

}
