package cm.pak.training.beans;

public abstract class AbstractData {
    protected boolean selected = false;
    protected String value ;

    public AbstractData() {
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

    public abstract String getTargetEntity() ;
}
