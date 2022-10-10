package cm.pak.data;

import cm.pak.annotations.SelectItem;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FieldData implements Serializable {
    private String name ;
    private String label ;
    private int sequence ;
    private String type ;
    private boolean editable ;
    private boolean updatable ;
    private boolean deletable ;
    private String metadata ;
    private String source;
    private Set<SelectItemData> selectItems ;
    private Set<FilterData> filters;
    private boolean nullable ;


    public FieldData() {
        editable = true ;
        nullable = true ;
        selectItems = new HashSet<>();
        filters = new HashSet<>();
    }

    public FieldData(String name, String label, int sequence, String type) {
        this.name = name;
        this.sequence = sequence;
        this.type = type;
        this.label = label ;
        selectItems = new HashSet<>();
        filters = new HashSet<>();
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

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public Set<SelectItemData> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(Set<SelectItemData> selectItems) {
        this.selectItems = selectItems;
    }

    public void addSelectItem(final SelectItemData item) {
          selectItems.add(item);
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Set<FilterData> getFilters() {
        return filters;
    }

    public void setFilters(Set<FilterData> filters) {
        this.filters = Collections.unmodifiableSet(filters);
    }

    public void addFilter(FilterData filter) {
        this.filters.add(filter);
    }


}
