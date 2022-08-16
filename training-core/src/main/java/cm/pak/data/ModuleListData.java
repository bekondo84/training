package cm.pak.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ModuleListData implements Serializable {

    private Set<String> modules = new HashSet<>();

    public ModuleListData() {
    }

    public Set<String> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    public void setModules(Set<String> modules) {
        this.modules = modules;
    }
}
