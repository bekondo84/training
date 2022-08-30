package cm.pak.services.impl;

import cm.pak.annotations.*;
import cm.pak.data.FieldData;
import cm.pak.data.GroupData;
import cm.pak.data.MetaData;
import cm.pak.data.SelectItemData;
import cm.pak.services.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Service
public class DefaultMetaService implements MetaService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetaService.class);

    @Override
    public MetaData getMeta(Class data) {
        final MetaData meta = new MetaData();
        final GlobalConfig config = (GlobalConfig) data.getDeclaredAnnotation(GlobalConfig.class);

        if (Objects.nonNull(config)) {
           meta.setCreatable(config.creatable());
           meta.setDeletable(config.deletable());
           meta.setUpdatable(config.updatable());
        }

        final SearchKey searchKey = (SearchKey) data.getDeclaredAnnotation(SearchKey.class);
        if (Objects.nonNull(searchKey)) {
            meta.setSearchKey(searchKey.value());
            meta.setLabel(searchKey.label());
        }

        final Groups annGroups = (Groups) data.getDeclaredAnnotation(Groups.class);
        Field[] fields = data.getDeclaredFields();
        meta.setName(data.getSimpleName());
        meta.setColumns(getColumns(data));
        meta.setFormTitle(data.getSimpleName().toLowerCase().concat(".form.title"));
        meta.setListTitle(data.getSimpleName().toLowerCase().concat(".list.title"));
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
            final Manytoone manytoone = field.getAnnotation(Manytoone.class);
            if (Objects.nonNull(manytoone) && manytoone.column()) {
                columns.add(new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), manytoone.sequence(), "many-to-one"));
            }
            final Select select = field.getAnnotation(Select.class);
            if (Objects.nonNull(select) && select.column()) {
                columns.add(new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), select.sequence(), "select"));
            }
        }
        return columns;
    }

    private GroupData createGroupData(Field[] fields, Group annGroup, Class clazz) {
        final GroupData group = new GroupData(annGroup.name(), annGroup.label(), annGroup.sequence());
        for (final  Field field : fields) {
            final Widget widget = field.getDeclaredAnnotation(Widget.class) ;
            field.setAccessible(true);
            if (Objects.nonNull(widget) && widget.group()!= null && widget.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), widget.sequence(), widget.value());
                fieldData.setEditable(widget.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(widget.updatable());
            }
            final Manytoone manytoone = field.getDeclaredAnnotation(Manytoone.class);
            if (Objects.nonNull(manytoone) && manytoone.group()!= null && manytoone.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), manytoone.sequence(), "many-to-one");
                fieldData.setEditable(manytoone.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(manytoone.updatable());
                fieldData.setSource(manytoone.source());
            }
            final Onetomany onetomany = field.getDeclaredAnnotation(Onetomany.class);
            if (Objects.nonNull(onetomany) && onetomany.group()!= null && onetomany.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), onetomany.sequence(), "one-to-many");
                fieldData.setEditable(onetomany.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(onetomany.updatable());
                fieldData.setSource(onetomany.source());
                if (Collection.class.isAssignableFrom(field.getType())) {
                    Class type = getParameterType(field);
                    fieldData.setMetadata(type.getName());
                }
            }
            final Select select = field.getDeclaredAnnotation(Select.class);
            if (Objects.nonNull(select) && select.group()!= null && select.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), select.sequence(), "select");
                fieldData.setEditable(select.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(select.updatable());
                for (SelectItem item: select.value()) {
                    fieldData.addSelectItem(new SelectItemData(item.name(), item.value()));
                }
            }
            final Manytomany manytomany = field.getDeclaredAnnotation(Manytomany.class);
            if (Objects.nonNull(manytomany) && manytomany.group()!= null && manytomany.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(),clazz.getSimpleName().concat(".").concat(field.getName()), manytomany.sequence(), "many-to-many");
                fieldData.setEditable(manytomany.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(manytomany.updatable());
                fieldData.setSource(manytomany.source());
                if (Collection.class.isAssignableFrom(field.getType())) {
                    Class type = getParameterType(field);
                    fieldData.setMetadata(type.getName());
                }
            }
        }
        return group;
    }

    private void initField(GroupData group, Field field, Widget widget, FieldData fieldData) {
        fieldData.setEditable(widget.editable());
        group.add(fieldData);
        fieldData.setMetadata(field.getType().getName());
        fieldData.setUpdatable(widget.updatable());
        if (Collection.class.isAssignableFrom(field.getType())) {
            Class type = getParameterType(field);
            fieldData.setMetadata(type.getName());
        }
    }

    private Class getParameterType(Field field) {
        final ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        Class type = (Class) parameterizedType.getActualTypeArguments()[0];
        return type;
    }
}
