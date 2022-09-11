package cm.pak.models.training;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_trth")
public class TrainingThemeModel extends ItemModel implements Serializable {
    @Column(unique = true, name = "t_code")
    private String code ;
    @Column(name = "t_name")
    private String name ;
    @Column(name = "t_desc")
    private String description;

    public TrainingThemeModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
