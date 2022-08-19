package cm.pak.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetaData implements Serializable {
    private String name ;
    private List<GroupData> groups ;
    private List<FieldData> columns ;

    public MetaData() {
        groups = new ArrayList<>();
        columns = new ArrayList<>();
    }

    public List<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupData> groups) {
        this.groups = groups;
    }

    public List<FieldData> getColumns() {
        return columns;
    }

    public void setColumns(List<FieldData> columns) {
        this.columns = columns;
    }

    public void add(final GroupData group) {
        groups.add(group);
    }

    public void  addColumn(final FieldData field) {
        this.columns.add(field);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "groups=" + groups +
                '}';
    }
}
