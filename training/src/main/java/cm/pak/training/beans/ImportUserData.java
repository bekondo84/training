package cm.pak.training.beans;

import cm.pak.annotations.*;
import cm.pak.data.FieldData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Groups({
        @Group(name = "general", label = "general.group"),
        @Group(name = "fields", label = "fields.group")
})
public class ImportUserData implements Serializable {
    @NotNull
    @Select(column = true, group = "general", value = {
            @SelectItem(name = "Interne", value ="I"),
            @SelectItem(name = "Externe", value ="E")
    })
    private String type ;
    @NotNull
    @Widget(value = "file", group = "general", pattern = ".csv")
    private String filename ;

    @NotNull
    @Manytomany(group = "fields", source = "/api/v1/users/fields")
    private Set<FieldData> fields ;

    public ImportUserData() {
            fields = new HashSet<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<FieldData> getFields() {
        return fields;
    }

    public void setFields(Set<FieldData> fields) {
        this.fields = fields;
    }

}
