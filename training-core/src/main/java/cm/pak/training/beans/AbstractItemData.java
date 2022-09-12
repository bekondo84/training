package cm.pak.training.beans;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;

@Groups({
        @Group(name = "admin", label = "admin.group", sequence = 100)
})
public abstract class AbstractItemData {
    protected boolean selected = false;
    protected String value ;
    @Widget(value = "number", group = "admin", editable = false)
    protected Long pk ;
    @Widget(value = "date", group = "admin", editable = false)
    protected String create ;
    @Widget(value = "date", group = "admin", editable = false)
    protected String update;

    public AbstractItemData() {
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public abstract String getTargetEntity() ;
}
