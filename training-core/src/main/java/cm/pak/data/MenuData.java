package cm.pak.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MenuData implements Serializable {
    private String name;
    private String label ;
    private String order ;
    private String listComponent ;
    private String viewComponent ;
    private String metadata;
    private String source ;
    private String viewMode ;
    private List<MenuData> children ;
    private List<ActionData> actions ;

    public MenuData() {
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getListComponent() {
        return listComponent;
    }

    public void setListComponent(String listComponent) {
        this.listComponent = listComponent;
    }

    public String getViewComponent() {
        return viewComponent;
    }

    public void setViewComponent(String viewComponent) {
        this.viewComponent = viewComponent;
    }

    public List<MenuData> getChildren() {
        return Collections.unmodifiableList(Objects.nonNull(children) ? children : new ArrayList<>());
    }

    public void setChildren(List<MenuData> children) {
        this.children = children;
    }

    public List<ActionData> getActions() {
        return actions;
    }

    public void setActions(List<ActionData> actions) {
        this.actions = actions;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getViewMode() {
        return viewMode;
    }

    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }


}
