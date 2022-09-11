package cm.pak.models.security;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_grou", joinColumns = @JoinColumn(name = "t_user"), inverseJoinColumns = @JoinColumn(name = "t_grou"))
    private Set<GroupeModel> profils;

    public UserModel() {
        super();
        profils = new HashSet<>();
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

    public Set<GroupeModel> getProfils() {
        return profils;
    }

    public void setProfils(Set<GroupeModel> profils) {
        this.profils = profils;
    }
    public void addProfil(final GroupeModel profil) {
         profils.add(profil);
    }
}
