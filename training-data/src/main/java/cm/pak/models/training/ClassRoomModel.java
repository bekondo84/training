package cm.pak.models.training;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_clro")
public class ClassRoomModel extends ItemModel implements Serializable {
    @Column(name = "t_code", unique = true)
    private String code;
    @Column(name = "t_inti")
    private String intitule;
    @Column(name = "t_adres")
    private String adresse;
    @Column(name = "t_loca")
    private String localisation;
    @Column(name = "t_abil" ,nullable = false)
    private Integer ability;

    public ClassRoomModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }
}
