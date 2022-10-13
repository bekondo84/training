package cm.pak.services.impl;

import cm.pak.annotations.*;
import cm.pak.data.*;
import cm.pak.services.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Service
public class DefaultMetaService implements MetaService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetaService.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public MetaData getMeta(Class clazz) {
        final MetaData meta = new MetaData();
        Class parent = clazz.getSuperclass();

        getMeta(parent, meta);
        getMeta(clazz, meta);

        return meta;
    }

    protected MetaData getMeta(Class data, final MetaData meta) {
        final GlobalConfig config = (GlobalConfig) data.getDeclaredAnnotation(GlobalConfig.class);
         final Locale locale = Locale.getDefault();
        if (Objects.nonNull(config)) {
           meta.setCreatable(config.creatable());
           meta.setDeletable(config.deletable());
           meta.setUpdatable(config.updatable());
        }

        final SearchKeys searchKeys = (SearchKeys) data.getDeclaredAnnotation(SearchKeys.class);
        if (Objects.nonNull(searchKeys)) {
            Optional<SearchKey> primarySearchKey = Arrays.stream(searchKeys.value())
                    .filter(se -> se.primary()).findAny();
            if (primarySearchKey.isPresent()) {
                final SearchKey searchKey = primarySearchKey.get();
                meta.setSearchKey(searchKey.value());
                meta.setLabel(getMessage(searchKey.label(), locale));
            } 
        }

        final Groups annGroups = (Groups) data.getDeclaredAnnotation(Groups.class);
        Field[] fields = data.getDeclaredFields();
        meta.setName(data.getSimpleName());
        meta.setColumns(getColumns(data));
        String form = data.getSimpleName().toLowerCase().concat(".form.title");
        meta.setFormTitle(getMessage(form, locale));
        String list = data.getSimpleName().toLowerCase().concat(".list.title");
        meta.setListTitle(getMessage(list, locale));
        if (Objects.nonNull(annGroups)) {
            for (Group annGroup: annGroups.value()) {
                final GroupData group = createGroupData(fields, annGroup, data, locale);
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

    private String getMessage(String searchKey, Locale locale) {
        return messageSource.getMessage(searchKey, null, searchKey, locale);
    }

    @Override
    public List<FieldData> getColumns(Class clazz)  {
        Field[] fields = clazz.getDeclaredFields();
        final List<FieldData> columns = new ArrayList<>();
        final Locale locale = Locale.getDefault();
        for (Field field : fields) {
            field.setAccessible(true);
            Optional.ofNullable(getColumn(clazz, locale, field))
                            .ifPresent(fieldData -> columns.add(fieldData));
        }
        return columns;
    }

    private FieldData getColumn(Class clazz, Locale locale, Field field) {
        final Widget annot = field.getAnnotation(Widget.class);
        String fieldName = clazz.getSimpleName().concat(".").concat(field.getName());
        if (Objects.nonNull(annot)) {
            return  new FieldData(field.getName(), getMessage(fieldName, locale), annot.sequence(), annot.value());
        }
        final Manytoone manytoone = field.getAnnotation(Manytoone.class);
        if (Objects.nonNull(manytoone)) {
            return new FieldData(field.getName(), getMessage(fieldName, locale), manytoone.sequence(), "many-to-one");
        }
        final Select select = field.getAnnotation(Select.class);
        if (Objects.nonNull(select)) {
            return new FieldData(field.getName(), getMessage(fieldName, locale), select.sequence(), "select");
        }
        return null;
    }

    @Override
    public Set<FieldData> getExportedFields(Class clazz) {
            final Field[] fields = clazz.getDeclaredFields();
            final Set<FieldData> result = new HashSet<>();
            long pk = 0 ;
            for (Field field : fields) {
                pk++ ;
                final FieldData data = getColumn(clazz, Locale.getDefault(), field);
                if (Objects.nonNull(data)) {
                    data.setPk(pk);
                    result.add(data);
                }
            }
            return  result;
    }

    private GroupData createGroupData(Field[] fields, Group annGroup, Class clazz, Locale locale) {
        final GroupData group = new GroupData(annGroup.name(), getMessage(annGroup.label(), locale), annGroup.sequence());
        for (final  Field field : fields) {
            final Widget widget = field.getDeclaredAnnotation(Widget.class) ;
            field.setAccessible(true);
            String fieldLabel = clazz.getSimpleName().concat(".").concat(field.getName());
            if (Objects.nonNull(widget) && widget.group()!= null && widget.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(), getMessage(fieldLabel, locale), widget.sequence(), widget.value());
                fieldData.setEditable(widget.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(widget.updatable());
                fieldData.setNullable(widget.nullable());
                if (StringUtils.hasText(widget.pattern())) {
                    fieldData.setPattern(widget.pattern());
                }
            }
            final Manytoone manytoone = field.getDeclaredAnnotation(Manytoone.class);
            if (Objects.nonNull(manytoone) && manytoone.group()!= null && manytoone.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(), getMessage(fieldLabel, locale), manytoone.sequence(), "many-to-one");
                fieldData.setEditable(manytoone.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(manytoone.updatable());
                fieldData.setSource(manytoone.source());
                processFilterIfExist(field, fieldData);
            }
            final Onetomany onetomany = field.getDeclaredAnnotation(Onetomany.class);
            if (Objects.nonNull(onetomany) && onetomany.group()!= null && onetomany.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(), getMessage(fieldLabel, locale), onetomany.sequence(), "one-to-many");
                fieldData.setEditable(onetomany.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setUpdatable(onetomany.updatable());
                fieldData.setDeletable(onetomany.deletable());
                fieldData.setSource(onetomany.source());
                if (Collection.class.isAssignableFrom(field.getType())) {
                    Class type = getParameterType(field);
                    fieldData.setMetadata(type.getName());
                }
            }
            final Select select = field.getDeclaredAnnotation(Select.class);
            if (Objects.nonNull(select) && select.group()!= null && select.group().equalsIgnoreCase(annGroup.name())) {
                FieldData fieldData = new FieldData(field.getName(), getMessage(fieldLabel, locale), select.sequence(), "select");
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
                final FieldData fieldData = new FieldData(field.getName(), getMessage(fieldLabel, locale), manytomany.sequence(), "many-to-many");
                fieldData.setEditable(manytomany.editable());
                group.add(fieldData);
                fieldData.setMetadata(field.getType().getName());
                fieldData.setDeletable(manytomany.deletable());
                fieldData.setSource(manytomany.source());
                if (Collection.class.isAssignableFrom(field.getType())) {
                    Class type = getParameterType(field);
                    fieldData.setMetadata(type.getName());
                }
                processFilterIfExist(field, fieldData);
            }
            /**
             * Process filter if existe
             */

        }
        return group;
    }

    private void processFilterIfExist(Field field, FieldData fieldData) {
        final Filters filters = field.getAnnotation(Filters.class);
        if (Objects.nonNull(filters)) {
            Arrays.stream(filters.value()).forEach(filter -> {
                final FilterData filterData = new FilterData(filter.field(), filter.value(), filter.operator());
                fieldData.addFilter(filterData);
            });
        }
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

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }
}
