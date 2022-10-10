package cm.pak.repositories;

import cm.pak.data.Connector;
import cm.pak.data.FilterData;
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
     * @param clazz
     * @param predicats
     * @param start
     * @param max
     * @param <T>
     * @return
     */
    <T extends ItemModel> List<T> findAll(Class<T> clazz, final Connector connector, final List<FilterData> predicats, int start, int max);

    <T extends ItemModel> List<T> search(Class<T> clazz, final String search, final List<FilterData> rules, int start, int max, final FilterData ...prefilter);
    /**
     *
     * @param query
     * @param <T>
     * @return
     */
    <T extends ItemModel> List<T> find(final String query) ;
}
