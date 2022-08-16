package cm.pak.repositories.impl;

import cm.pak.models.security.base.ItemModel;
import cm.pak.repositories.FlexibleSearch;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

import static cm.pak.PakTrainigCons.PAK_PU;

@Repository
public class DefaultFlexibleSearch implements FlexibleSearch {

    @PersistenceContext(unitName = PAK_PU)
    protected EntityManager em ;

    @Override
    public <T extends ItemModel> T find(Class<T> clazz, String fieldName, Object key) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);
        query.where(builder.equal(root.get(fieldName), key));
        TypedQuery<T> typedQuery = em.createQuery(query);

        return typedQuery.getSingleResult();
    }

    @Override
    public <T extends ItemModel> T find(Class<T> clazz, Object key) {
        assert Objects.nonNull(clazz) : "No class found " ;
        assert Objects.nonNull(key) : "No Key found";

        return em.find(clazz, key);
    }

    @Override
    public <T extends ItemModel> List<T> findAll(Class<T> clazz) {
        assert Objects.nonNull(clazz) : "No class found " ;

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);
        TypedQuery<T> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }

    @Override
    public <T extends ItemModel> List<T> find(String queryString) {
        assert StringUtils.hasLength(queryString) : "Query can't be empty";

        final Query query = em.createQuery(queryString);

        return query.getResultList();
    }
}
