package cm.pak.services;

import cm.pak.annotations.Widget;
import cm.pak.data.FieldData;
import cm.pak.data.MetaData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface MetaService {

   /**
    *
    * @param clazz
    * @return
    */
   MetaData getMeta(final Class clazz) ;

   List<FieldData> getColumns(final Class clazz) ;
}
