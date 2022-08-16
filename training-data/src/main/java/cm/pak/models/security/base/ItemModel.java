package cm.pak.models.security.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class ItemModel implements Serializable {

    @Column(name = "p_pk", updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long pk ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "p_create")
    protected Date create ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "p_update")
    protected Date update;

    public ItemModel() {
    }

    public ItemModel(Long pk, Date create, Date update) {
        this.pk = pk;
        this.create = create;
        this.update = update;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || pk ==null || getClass() != o.getClass()) return false;
        ItemModel itemModel = (ItemModel) o;
        return pk.equals(itemModel.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
