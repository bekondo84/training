package cm.pak.models.training;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_trai")
public class TrainingModel extends ItemModel implements Serializable {

    @Column(name = "t_name", unique = true)
    private String name ;
    @Column(name = "t_desc")
    private String description;
    @Column(name = "t_fude")
    private String fullDescription;
    @ManyToOne
    @JoinColumn(name = "th_id")
    private TrainingThemeModel theme ;
    @Column(name = "t_seq")
    private Integer sequence;
    @Column(name = "t_ima")
    private String image ;
    @Column(name = "t_act")
    private boolean activate;

    public TrainingModel() {
        this.activate = false ;
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

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public TrainingThemeModel getTheme() {
        return theme;
    }

    public void setTheme(TrainingThemeModel theme) {
        this.theme = theme;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}
