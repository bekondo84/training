package cm.pak.models;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_mate")
public class MailTemplateModel extends ItemModel implements Serializable {
    @Column(name = "t_code", unique = true)
    private String code ;
    @Column(name = "t_inti")
    private String intitule ;
    @Column(name = "t_text")
    @Lob
    private String text ;

    public MailTemplateModel() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MailTemplateModel{" +
                "code='" + code + '\'' +
                ", intitule='" + intitule + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
