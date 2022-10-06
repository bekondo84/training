package cm.pak.training.beans;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group")
})
public class ImportData implements Serializable {
    @Widget(value = "datetime-local", group = "general")
    private String date ;
    @Widget(value = "file", group = "general")
    private CommonsMultipartFile filename ;


    public ImportData() {

    }

    public CommonsMultipartFile getFilename() {
        return filename;
    }

    public void setFilename(CommonsMultipartFile filename) {
        this.filename = filename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ImportData{" +
                "date='" + date + '\'' +
                ", filename=" + filename +
                '}';
    }
}
