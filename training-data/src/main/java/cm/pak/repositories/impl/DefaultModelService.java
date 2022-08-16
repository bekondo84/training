package cm.pak.repositories.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.base.ItemModel;
import cm.pak.repositories.ModelService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

import static cm.pak.PakTrainigCons.PAK_PU;

@Repository
public class DefaultModelService implements ModelService {

    @PersistenceContext(unitName = PAK_PU)
    protected EntityManager em ;

    @Override
    public <T extends ItemModel> T createOrUpdate(T entity) throws ModelServiceException {
        assert Objects.nonNull(entity) : "Entity to create or update must be not null" ;

        T result = null ;
        try {
            if (Objects.isNull(((ItemModel) entity).getPk())) {
                em.persist(entity);
            } else {
                result = em.merge(entity);
            }
        } catch (Exception ex) {
            throw new ModelServiceException(ex);
        }
        return result;
    }

    @Override
    public <T extends ItemModel> void remove(T entity) {
        assert Objects.nonNull(entity) : "Entity to remove must be not null" ;

        em.remove(em.find(entity.getClass(), ((ItemModel)entity).getPk()));
    }

    @Override
    public <T extends ItemModel> T find(Class<T> clazz, Object key) {
        assert Objects.nonNull(clazz) : "No class found " ;
        assert Objects.nonNull(key) : "No Key found";

        return em.find(clazz, key);
    }

}
