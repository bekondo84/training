package cm.pak.models.security;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_USER")
public class UserModel extends ItemModel implements Serializable {

    @Column(name = "p_code", nullable = false, unique = true)
     private String code ;

    @Column(name = "p_name")
    private String name ;

    @Column(name = "p_categ")
    private String categorie ;

    @Column(name = "p_genre")
    private String genre ;

    @Column(name = "t_pwd")
    private String password ;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupeModel groupeModel;

    public UserModel() {
        super();
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GroupeModel getGroupe() {
        return groupeModel;
    }

    public void setGroupe(GroupeModel groupeModel) {
        this.groupeModel = groupeModel;
    }
}
