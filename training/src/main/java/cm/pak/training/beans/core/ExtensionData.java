package cm.pak.training.beans.core;

import cm.pak.annotations.*;
import cm.pak.data.MenuData;
import cm.pak.models.core.ExtensionModel;
import cm.pak.training.beans.AbstractItemData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalConfig(creatable = false,updatable = false,deletable = false)
@Groups({
        @Group(name = "general", label ="general.group", sequence = 1),
        @Group(name = "description", label = "description.group", sequence = 2)})
@SearchKeys({
        @SearchKey(value = "pk", label = "pk", primary = true),
        @SearchKey(value = "code", label = "code"),
        @SearchKey(value = "version", label = "version"),
        @SearchKey(value = "install", label = "install"),
        @SearchKey(value = "shortDescription", label = "shortDescription"),
        @SearchKey(value = "longDescription", label = "longDescription")
})
public class ExtensionData extends AbstractItemData implements Serializable {
    @Widget(value = "text", group = "general", column = true)
    private String code ;
    @Widget(value = "text", group = "general", column = true)
    private String version ;
    @Widget(value = "number", group = "general", column = true)
    private int sequence ;
    @Widget(value = "checkbox", group = "general", column = true)
    private boolean install ;
    @Widget(value = "text", group = "general", column = true)
    private String shortDescription ;
    @Widget(value = "textarea", group = "description", column = false)
    private String longDescription ;
    @Widget(value = "text", group = "general", column = true)
    private String owner ;
    private String name ;
    private List<MenuData> menus ;

    public ExtensionData() {
        super();
        this.menus = new ArrayList<>();

    }

    @Override
    public String getTargetEntity() {
        return ExtensionModel.class.getName();
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public List<MenuData> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuData> menus) {
        this.menus = menus;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isInstall() {
        return install;
    }

    public void setInstall(boolean install) {
        this.install = install;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtensionData that = (ExtensionData) o;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }

    @Override
    public String toString() {
        return "ExtensionData{" +
                "pk=" + pk +
                ", create=" + create +
                ", update=" + update +
                ", code='" + code + '\'' +
                ", version='" + version + '\'' +
                ", sequence=" + sequence +
                ", install=" + install +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", owner='" + owner + '\'' +
                ", menus=" + menus +
                '}';
    }
}
