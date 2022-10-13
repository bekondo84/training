package cm.pak.repositories.impl;

import cm.pak.data.Connector;
import cm.pak.data.FilterData;
import cm.pak.models.security.base.ItemModel;
import cm.pak.repositories.FlexibleSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cm.pak.PakTrainigCons.PAK_PU;

@Repository
public class DefaultFlexibleSearch implements FlexibleSearch {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultFlexibleSearch.class);
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

    @Override
    public <T extends ItemModel> List<T> findAll(Class<T> clazz, final Connector connector, List<FilterData> filters, int start, int max) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<T> from = criteriaQuery.from(clazz);

        if (!CollectionUtils.isEmpty(filters)) {

        }
        Query query = em.createQuery(criteriaQuery);
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.getResultList();
    }

    /**
     *
     * @param clazz
     * @param search
     * @param rules
     * @param start
     * @param max
     * @param prefilter
     * @param <T>
     * @return
     */
    @Override
    public <T extends ItemModel> List<T> search(Class<T> clazz, List<FilterData> rules, int start, int max, FilterData... prefilter) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<T> from = criteriaQuery.from(clazz);
        Predicate leftCondition = null;
        if (Objects.nonNull(prefilter)) {
           final List<Predicate> predicates = new ArrayList<>();
           for (FilterData filter :  prefilter) {
               predicates.add(predicatBuilder(criteriaBuilder, from, filter));
           }
          //  LOG.info(String.format("....................................... %s ::: %s", prefilter.length, predicates.size()));
           if (predicates.size()>1) {
               leftCondition = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
           } else if (predicates.size() == 1){
               leftCondition = predicates.get(0);
           }
        }
        Predicate rigthCondition = null ;
        if (!CollectionUtils.isEmpty(rules) ) {

        }

        if (Objects.nonNull(rigthCondition) && Objects.nonNull(leftCondition)) {
           final Predicate condition = criteriaBuilder.and(leftCondition, rigthCondition) ;
            criteriaQuery.where(condition);
        } else if (Objects.nonNull(leftCondition)) {
            criteriaQuery.where(leftCondition);
        } else if (Objects.nonNull(rigthCondition)) {
            criteriaQuery.where(rigthCondition);
        }
        Query query = em.createQuery(criteriaQuery);
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.getResultList();
    }

    private <T extends ItemModel> Predicate predicatBuilder(CriteriaBuilder criteriaBuilder, Root<T> from, FilterData filter) {
        if (filter.getOperator().equals("ne")) {
            return criteriaBuilder.notEqual(getPath(from, filter.getField()), filter.getValue());
        }
        return criteriaBuilder.equal(getPath(from, filter.getField()), filter.getValue());
    }

    private <X> Path<X> getPath(Root<X> root, String fieldname){
        Path<X> path = root;
        for(String part : fieldname.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }
}
