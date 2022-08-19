package cm.pak.data;

import java.io.Serializable;

public class FieldData implements Serializable {
    private String name ;
    private String label ;
    private int sequence ;
    private String type ;

    public FieldData() {
    }

    public FieldData(String name, String label, int sequence, String type) {
        this.name = name;
        this.sequence = sequence;
        this.type = type;
        this.label = label ;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FieldData{" +
                "name='" + name + '\'' +
                ", sequence=" + sequence +
                ", type='" + type + '\'' +
                '}';
    }
}
