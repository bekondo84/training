package cm.pak.services;

import cm.pak.data.FieldData;
import cm.pak.data.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface MetaService {
    static final Logger LOG = LoggerFactory.getLogger(MetaService.class);
   /**
    *
    * @param clazz
    * @return
    */
   default MetaData getMeta(final String clazz) throws ClassNotFoundException {
      final Class objClass = Class.forName(clazz);
      return  getMeta(objClass);
   }
   /**
    *
    * @param clazz
    * @return
    */
   MetaData getMeta(final Class clazz) ;

   List<FieldData> getColumns(final Class clazz) ;
}
