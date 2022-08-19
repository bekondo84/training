package cm.pak.services.impl;

import cm.pak.annotations.Group;
import cm.pak.annotations.Groups;
import cm.pak.annotations.Widget;
import cm.pak.data.FieldData;
import cm.pak.data.GroupData;
import cm.pak.data.MetaData;
import cm.pak.services.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultMetaService implements MetaService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetaService.class);

    @Override
    public MetaData getMeta(Class data) {
        Groups annGroups = (Groups) data.getDeclaredAnnotation(Groups.class);
        Field[] fields = data.getDeclaredFields();
        final MetaData meta = new MetaData();
        meta.setName(data.getSimpleName());
        meta.setColumns(getColumns(data));

        if (Objects.nonNull(annGroups)) {
            for (Group annGroup: annGroups.value()) {
                final GroupData group = createGroupData(fields, annGroup, data);
                meta.add(group);
            }
            meta.getGroups().sort(new Comparator<GroupData>() {
                @Override
                public int compare(GroupData o1, GroupData o2) {
                    return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
                }
            });
        }
        //LOG.info(String.format("Meta Data : %s", meta));
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
                columns.add(new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), annot.sequence(), annot.value()));
            }
        }
        return columns;
    }

    private GroupData createGroupData(Field[] fields, Group annGroup, Class clazz) {
        final GroupData group = new GroupData(annGroup.name(), annGroup.label(), annGroup.sequence());
        for (final  Field field : fields) {
            final Widget widget = field.getDeclaredAnnotation(Widget.class) ;
            if (Objects.nonNull(widget) && widget.group()!= null && widget.group().equalsIgnoreCase(annGroup.name())) {
                field.setAccessible(true);
                FieldData fieldData = new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), widget.sequence(), widget.value());
                group.add(fieldData);
            }
        }
        return group;
    }
}
