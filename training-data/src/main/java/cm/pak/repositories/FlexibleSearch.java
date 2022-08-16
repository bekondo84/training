package cm.pak.repositories;

import cm.pak.models.security.base.ItemModel;

import java.util.List;

public interface FlexibleSearch {

    /**
     *
     * @param clazz
     * @param key
     * @param <T>
     * @return
     */
    <T extends ItemModel> T find (Class<T> clazz, Object key);

    <T extends ItemModel> T find (Class<T> clazz, final String fieldName, Object key);
    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T extends ItemModel> List<T> findAll(Class<T> clazz) ;

    /**
     *
     * @param query
     * @param <T>
     * @return
     */
    <T extends ItemModel> List<T> find(final String query) ;
}
