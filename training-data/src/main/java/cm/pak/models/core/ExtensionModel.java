package cm.pak.models.core;

import cm.pak.models.security.base.ItemModel;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_ext")
public class ExtensionModel extends ItemModel {

    @Column(name = "t_code", unique = true, updatable = false)
    private String code ;

    @Column(name = "t_vers")
    private String version ;

    @Column(name = "t_shortD")
    private String shortDescription ;

    @Column(name = "t_longD")
    private String longDescription ;

    @Column(name = "t_owne")
    private String owner ;

    private int sequence ;

    @ElementCollection
    @JoinColumn(name = "t_dep", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<String> depends ;

    @Column(name = "t_inst")
    private boolean install ;

    @Transient
    private Set<AbstractMenu> menus ;


    public ExtensionModel() {
        super(null, new Date(), null);
        this.depends = new HashSet<>();
        this.install = false ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isInstall() {
        return install;
    }

    public void setInstall(boolean install) {
        this.install = install;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<String> getDepends() {
        return depends;
    }

    public void setDepends(Set<String> depends) {
        this.depends = depends;
    }

    public Set<AbstractMenu> getMenus() {
        return Collections.unmodifiableSet(menus);
    }

    public void setMenus(Set<AbstractMenu> menus) {
        this.menus = menus;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "ExtensionModel{" +
                "Pk=" +pk+
                "code='" + code + '\'' +
                ", version='" + version + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", owner='" + owner + '\'' +
                ", depends=" + depends +
                '}';
    }
}
