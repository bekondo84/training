package cm.pak.repositories;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.base.ItemModel;

import java.util.List;

public interface ModelService {

    /**
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T extends ItemModel> T createOrUpdate(T entity) throws ModelServiceException;


    /**
     *
     * @param entity
     * @param <T>
     */
    <T extends ItemModel> void remove(T entity) ;
    /**
     *
     * @param clazz
     * @param key
     * @param <T>
     * @return
     */
    <T extends ItemModel> T find (Class<T> clazz, Object key);
}
