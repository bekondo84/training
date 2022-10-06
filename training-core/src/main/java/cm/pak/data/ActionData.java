package cm.pak.data;

import cm.pak.models.security.AccesRigth;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionData implements Serializable {
    private String name ;
    private String label ;
    private String source ;
    private String listComponent;
    private String viewComponent;
    private String method ;
    private String icon;
    private String scope ;
    private String type;
    private String metadata;
    private boolean active ;
    private boolean canRead;
    private boolean canWrite ;
    private boolean canCreate ;
    private boolean canDelete;


    public ActionData() {
        this.active = false;
        this.canCreate= false;
        this.canWrite = false;
        this.canDelete = false;
        this.canRead = false;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public void setRigth(final AccesRigth access) {
        this.canWrite = access.isCanwrite();
        this.canCreate = access.isCancreate();
        this.canRead = access.isCanread();
        this.canDelete = access.isCandelete();
    }
    public void computedActivation() {
        this.active = this.canCreate||this.canDelete||this.canRead||this.canWrite;
    }
}
