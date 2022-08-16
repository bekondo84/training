package cm.pak.services.impl;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.data.FieldData;
import cm.pak.data.GroupData;
import cm.pak.data.MetaData;
import cm.pak.services.MetaService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultMetaService implements MetaService {
    @Override
    public MetaData getMeta(Class data) {
        Groups annGroups = (Groups) data.getDeclaredAnnotation(Groups.class);
        Field[] fields = data.getDeclaredFields();
        final MetaData meta = new MetaData();

        if (Objects.nonNull(annGroups)) {
            for (Group annGroup: annGroups.value()) {
                final GroupData group = createGroupData(fields, annGroup);
                meta.add(group);
            }
            meta.getGroups().sort(new Comparator<GroupData>() {
                @Override
                public int compare(GroupData o1, GroupData o2) {
                    return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
                }
            });
        }
        return meta;
    }

    @Override
    public List<FieldData> getColumns(Class clazz)  {
        Field[] fields = clazz.getDeclaredFields();
        final List<FieldData> columns = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            final Widget annot = field.getAnnotation(Widget.class);

            if (Objects.nonNull(annot) && annot.column()) {
                columns.add(new FieldData(field.getName(), annot.sequence(), annot.value()));
            }
        }
        return columns;
    }

    private GroupData createGroupData(Field[] fields, Group annGroup) {
        final GroupData group = new GroupData(annGroup.name(), annGroup.label(), annGroup.sequence());
        for (final  Field field : fields) {
            final Widget widget = field.getAnnotation(Widget.class) ;
            if (Objects.nonNull(widget)) {
                field.setAccessible(true);
                FieldData fieldData = new FieldData(field.getName(), widget.sequence(), widget.value());
                group.add(fieldData);
            }
        }
        return group;
    }
}
