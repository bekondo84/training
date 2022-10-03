package cm.pak.models.security;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name = "t_acri")
@Entity
public class AccesRigth extends ItemModel implements Serializable {
    @Column(name = "t_nam")
    private String name ;
    @Column(name = "t_lab")
    private String label;
    @Column(name = "t_rea")
    private boolean canread;
    @Column(name = "t_wri")
    private boolean canwrite;
    @Column(name = "t_cre")
    private boolean cancreate;
    @Column(name = "t_del")
    private boolean candelete;

    public AccesRigth() {
        super();
    }

    public AccesRigth(Date create, String name, String label) {
        super(null, create, null);
        this.name = name;
        this.label = label;
        this.canread = false;
        this.canwrite = false;
        this.cancreate = false;
        this.candelete = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanread() {
        return canread;
    }

    public void setCanread(boolean canread) {
        this.canread = canread;
    }

    public boolean isCanwrite() {
        return canwrite;
    }

    public void setCanwrite(boolean canwrite) {
        this.canwrite = canwrite;
    }

    public boolean isCancreate() {
        return cancreate;
    }

    public void setCancreate(boolean cancreate) {
        this.cancreate = cancreate;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccesRigth that = (AccesRigth) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "AccesRigth{" +
                "Pk='"+ pk+ '\''+
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", canread=" + canread +
                ", canwrite=" + canwrite +
                ", cancreate=" + cancreate +
                ", candelete=" + candelete +
                '}';
    }
}
