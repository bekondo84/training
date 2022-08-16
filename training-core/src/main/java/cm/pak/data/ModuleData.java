package cm.pak.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ModuleData {
    private String version ;
    private String name ;
    private String description ;
    private String longDescription ;
    private String owner;
    private Integer sequence;
    private boolean autoInstall ;
    private Set<String> depends ;
    private Set<MenuData> menus ;

    public ModuleData() {
        this.depends = new HashSet<>();
        this.menus = new HashSet<>();
        this.autoInstall = false ;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
        return Collections.unmodifiableSet(depends);
    }

    public void setDepends(Set<String> depends) {
        this.depends = depends;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public boolean isAutoInstall() {
        return autoInstall;
    }

    public void setAutoInstall(boolean autoInstall) {
        this.autoInstall = autoInstall;
    }

    public Set<MenuData> getMenus() {
        return Collections.unmodifiableSet(menus);
    }

    public void setMenus(Set<MenuData> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "ModuleData{" +
                "version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", owner='" + owner + '\'' +
                ", depends=" + depends +
                ", menus=" + menus +
                '}';
    }
}
