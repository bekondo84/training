package cm.pak.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetaData implements Serializable {

    private List<GroupData> groups ;

    public MetaData() {
        groups = new ArrayList<>();
    }

    public List<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupData> groups) {
        this.groups = groups;
    }

    public void add(final GroupData group) {
        groups.add(group);
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "groups=" + groups +
                '}';
    }
}
