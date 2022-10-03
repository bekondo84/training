package cm.pak.models.core;

import cm.pak.models.security.base.ItemModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_mail")
public class MailModel extends ItemModel implements Serializable {
    @Column(name = "t_to")
    private String to ;
    @Column(name = "t_subj")
    private String subject ;
    @Lob
    @Column(name = "t_text")
    private String text;

    public MailModel() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
