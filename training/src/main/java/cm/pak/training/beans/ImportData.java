package cm.pak.training.beans;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group")
})
public class ImportData implements Serializable {
    @Widget(value = "datetime-local", group = "general")
    private String date ;
    @Widget(value = "file", group = "general")
    private String filename ;


    public ImportData() {

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
