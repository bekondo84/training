package cm.pak.populators;

import cm.pak.models.security.base.ItemModel;

/**
 * The purpose of this class is to convert
 * @param <T>
 * @param <Y>
 */
public interface Populator<T extends ItemModel, Y extends Object> {

    /**
     * Transform Object of type T sub type of ItemModel to an object of type Y
     * @param source
     * @return
     */
    Y popule (final T source) ;

    /**
     *
     * @param source
     * @return
     */
    T revert (final  Y source) ;
}
