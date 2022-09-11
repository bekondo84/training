package cm.pak.populators;

import cm.pak.models.security.base.ItemModel;
import cm.pak.training.beans.AbstractData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * The purpose of this class is to convert
 * @param <T>
 * @param <Y>
 */
public interface Populator<T extends ItemModel, Y extends AbstractData> {
    final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    final static Logger LOG = LoggerFactory.getLogger(Populator.class);
    /**
     * Transform Object of type T sub type of ItemModel to an object of type Y
     * @param source
     * @return
     */
    Y populate(final T source) ;

    /**
     *
     * @param source
     * @return
     */
    T revert (final  Y source) throws ParseException;

    default void populate(final T source, Y cible) {
          if (Objects.nonNull(source.getCreate())) {
              cible.setCreate(SDF.format(source.getCreate()));
          }
          if (Objects.nonNull(source.getUpdate())) {
              cible.setUpdate(SDF.format(source.getUpdate()));
          }
           cible.setPk(source.getPk());
    }
    default void revert(final Y source, final T cible)  {
       try {
           if (StringUtils.hasLength(source.getCreate())) {
               cible.setCreate(SDF.parse(source.getCreate()));
           }
           if (StringUtils.hasLength(source.getUpdate())) {
               cible.setUpdate(SDF.parse(source.getUpdate()));
           }
       } catch (ParseException ex) {
           LOG.error(ex.getMessage());
       }
        cible.setPk(source.getPk());
    }
}
