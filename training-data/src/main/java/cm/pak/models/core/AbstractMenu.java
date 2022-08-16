package cm.pak.models.core;

import cm.pak.models.security.base.ItemModel;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "t_fonc")
public abstract class AbstractMenu extends ItemModel implements Serializable {

    @Column(name = "t_name", nullable = false)
    protected String name ;
    @Column(name = "t_ord", nullable = false)
    protected String order ;

    @Column(name = "t_path")
    protected String path ;

    @Column(name = "t_type")
    protected String type ;

    /**
    @ManyToOne
    @JoinColumn(name = "t_ext")
    protected ExtensionModel module ;
    **/

    public AbstractMenu(String type) {
        this.type = type;
    }

    public AbstractMenu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
    public ExtensionModel getModule() {
        return module;
    }

    public void setModule(ExtensionModel module) {
        this.module = module;
    }
     **/



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractMenu that = (AbstractMenu) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
