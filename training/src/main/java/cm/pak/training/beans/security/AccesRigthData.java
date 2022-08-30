package cm.pak.training.beans.security;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.models.security.AccesRigth;
import cm.pak.training.beans.AbstractData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "admin", label = "admin.group" ,sequence = 100),
        @Group(name = "general", label = "general.group", sequence = 2)
})
public class AccesRigthData extends AbstractData implements Serializable {
    @Widget(value = "number", group = "admin", editable = false)
    private Long pk ;
    private Date create ;
    @Widget(value = "date", group = "admin", editable = false)
    private Date update;
    @Widget(value = "date", group = "admin", editable = false)
    private String label ;
    @Widget(value = "text", group = "general", column = true)
    private String name ;
    @Widget(value = "checkbox", group = "general", column = true)
    private boolean read;
    @Widget(value = "checkbox", group = "general", column = true)
    private boolean write;
    @Widget(value = "checkbox", group = "general", column = true)
    private boolean cancreate;
    @Widget(value = "checkbox", group = "general", column = true)
    private boolean delete;

    public AccesRigthData() {
        this.read = false ;
        this.write = false ;
        this.delete = false;
        this.cancreate = false;
    }

    @Override
    public String getTargetEntity() {
        return AccesRigth.class.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public boolean isCancreate() {
        return cancreate;
    }

    public void setCancreate(boolean cancreate) {
        this.cancreate = cancreate;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
