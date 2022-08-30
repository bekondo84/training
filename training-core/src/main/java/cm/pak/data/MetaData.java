package cm.pak.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetaData implements Serializable {
    private String name ;
    private boolean creatable ;
    private boolean updatable;
    private boolean deletable ;
    protected String listTitle ;
    protected String formTitle ;
    protected String searchKey ;
    protected String label ;
    protected List<GroupData> groups ;
    protected List<FieldData> columns ;


    public MetaData() {
        groups = new ArrayList<>();
        columns = new ArrayList<>();
        this.creatable = true;
        this.updatable = true ;
        this.deletable = true ;
        this.searchKey = "pk";
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

    public boolean isCreatable() {
        return creatable;
    }

    public void setCreatable(boolean creatable) {
        this.creatable = creatable;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
