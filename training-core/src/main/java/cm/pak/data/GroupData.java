package cm.pak.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupData implements Serializable {
    private String name ;
    private String label ;
    private int sequence ;
    private List<FieldData> fields ;

    public GroupData() {
        fields = new ArrayList<>();
    }

    public GroupData(String name, String label, int sequence) {
        this.name = name;
        this.label = label;
        this.sequence = sequence;
        fields = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void add(final FieldData field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", sequence=" + sequence +
                ", fields=" + fields +
                '}';
    }
}
