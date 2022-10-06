package cm.pak.data;

import cm.pak.models.security.AccesRigth;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    private boolean show ;
    private boolean canRead;
    private boolean canWrite ;
    private boolean canCreate ;
    private boolean canDelete;
    private boolean canAccess ;

    public MenuData() {
        this.children = new ArrayList<>();
        this.show = false;
        this.canCreate= false;
        this.canWrite = false;
        this.canDelete = false;
        this.canRead = false;
        this.canAccess = false;
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
        return Objects.nonNull(children) ? children : new ArrayList<>();
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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    public boolean isCanCreate() {
        return canCreate;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public void computedIfShow() {
        this.canAccess = canRead || canDelete || canWrite || canCreate;
    }

    public void setRigth(final AccesRigth acces) {
        canCreate = acces.isCancreate();
        canWrite = acces.isCanwrite();
        canDelete = acces.isCandelete();
        canRead = acces.isCanread();
    }

    public boolean isCanAccess() {
        return canAccess;
    }

    public void setCanAccess(boolean canAccess) {
        this.canAccess = canAccess;
    }
}
