package cm.pak.services;

import cm.pak.data.FieldData;
import cm.pak.data.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public interface MetaService {
    static final Logger LOG = LoggerFactory.getLogger(MetaService.class);

     Set<FieldData> getExportedFields(final Class clazz) ;
   /**
    *
    * @param clazz
    * @return
    */
   default MetaData getMeta(final String clazz) throws ClassNotFoundException {
      final Class objClass = Class.forName(clazz);
      return  getMeta(objClass);
   }

   default Object getInstance(final String clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      final Class objClass = Class.forName(clazz);
      return objClass.newInstance();
   }
   /**
    *
    * @param clazz
    * @return
    */
   MetaData getMeta(final Class clazz) ;

   List<FieldData> getColumns(final Class clazz) ;

   default Object convert(final Class clazz, final String fieldname, Object value)  {
      final Field field;
      try {
         field = clazz.getDeclaredField(fieldname);
         if (Boolean.class.isAssignableFrom(field.getType())
                 || boolean.class.isAssignableFrom(field.getType())) {
            return new Boolean((String)value);
         }
      } catch (NoSuchFieldException e) {
         return null ;
      }
      return value;
   }

   MessageSource getMessageSource() ;
}
