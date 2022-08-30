package cm.pak.training.beans.security;

import cm.pak.annotations.*;
import cm.pak.training.beans.AbstractData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group"),
        @Group(name = "admin", label = "admin.group", sequence = 05),
        @Group(name = "security", label = "security.group", sequence = 2)
})
@SearchKey(value = "code", label = "code")
public class UserData extends AbstractData implements Serializable {

    @Widget(value = "number", group = "admin")
    private Long pk ;
    @Widget(value = "date", group = "admin")
    private Date create ;
    @Widget(value = "text", column = true, group = "general")
    private String code ;
    @Widget(value = "date", group = "admin")
    private Date update ;
    @Widget(value = "text", column = true, group = "general")
    private String name ;
    @Select(column = true, group = "general", value = {
            @SelectItem(name = "Masculin", value ="M"),
            @SelectItem(name = "Feminin", value ="F")
    })
    private String genre ;
    @Select(column = true, group = "general", value = {
            @SelectItem(name = "Interne", value ="I"),
            @SelectItem(name = "Externe", value ="E")
    })
    private String category ;

    @Manytomany(group ="security", source = "/api/v1/groups")
    private List<GroupeData> profils ;

    public UserData() {
    }

    @Override
    public String getTargetEntity() {
        return null;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<GroupeData> getProfils() {
        return profils;
    }

    public void setProfils(List<GroupeData> profils) {
        this.profils = profils;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return pk.equals(userData.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
