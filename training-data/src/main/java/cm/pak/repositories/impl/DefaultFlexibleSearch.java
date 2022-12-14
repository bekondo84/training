package cm.pak.repositories.impl;

import cm.pak.data.Connector;
import cm.pak.data.FilterData;
import cm.pak.data.PaginationData;
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

        Query query = em.createQuery(criteriaQuery);
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.getResultList();
    }

    /**
     *
     * @param clazz
     * @param rules
     * @param start
     * @param max
     * @param prefilter
     * @param <T>
     * @return
     */
    @Override
    public <T extends ItemModel> PaginationData<T> search(Class<T> clazz, List<FilterData> rules, int start, int max, FilterData... prefilter) {
        final PaginationData result = new PaginationData<>();
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaBuilder countCB = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<T> from = criteriaQuery.from(clazz);
        final CriteriaQuery<Long> count = countCB.createQuery(Long.class);
        final Root countRoot = count.from(clazz);
        count.select(criteriaBuilder.count(countRoot));
        Predicate leftCondition = null;
        Predicate countLFC = null ;
        if (Objects.nonNull(prefilter)) {
            leftCondition = getPreFilterPredicates(criteriaBuilder, from, leftCondition, prefilter);
            countLFC = getPreFilterPredicates(countCB, countRoot, countLFC, prefilter);
        }
        Predicate rigthCondition = null ;
        Predicate countRC = null ;
        if (!CollectionUtils.isEmpty(rules) ) {
            rigthCondition = getSearchPredicates(rules, criteriaBuilder, from, rigthCondition);
            countRC = getSearchPredicates(rules, countCB, countRoot, countRC);
        }

        if (Objects.nonNull(rigthCondition) && Objects.nonNull(leftCondition)) {
           final Predicate condition = criteriaBuilder.and(leftCondition, rigthCondition) ;
           final Predicate countC = countCB.and(countLFC, countRC);
            criteriaQuery.where(condition);
            count.where(countC);
        } else if (Objects.nonNull(leftCondition)) {
            criteriaQuery.where(leftCondition);
            count.where(countLFC);
        } else if (Objects.nonNull(rigthCondition)) {
            criteriaQuery.where(rigthCondition);
            count.where(countRC);
        }
        result.setTotalPages(getTotalPages(max, count));
        result.setCurrentPage(start + 1);
        Query query = em.createQuery(criteriaQuery);

        if (result.getTotalPages() > 1) {
            query.setFirstResult(start);
            query.setMaxResults(max);
        } else {
            result.setCurrentPage(1);
        }
        result.setItems(query.getResultList());

        return result;
    }

    private int getTotalPages(int max, CriteriaQuery<Long> count) {
        int nbreofitems = em.createQuery(count).getSingleResult().intValue();

        if (nbreofitems%max == 0) {
            return nbreofitems/max;
        }
        return (nbreofitems/max)+1;
    }

    private <T extends ItemModel> Predicate getSearchPredicates(List<FilterData> rules, CriteriaBuilder criteriaBuilder, Root<T> from, Predicate rigthCondition) {
        final List<Predicate> predicates = new ArrayList<>() ;
        rules.forEach(rule -> predicates.add(predicatBuilder(criteriaBuilder, from, rule)));
        if (predicates.size()>1) {
            rigthCondition = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        } else if (predicates.size() == 1){
            rigthCondition = predicates.get(0);
        }
        return rigthCondition;
    }

    private <T extends ItemModel> Predicate getPreFilterPredicates(CriteriaBuilder criteriaBuilder, Root<T> from, Predicate leftCondition, FilterData[] prefilter) {
        final List<Predicate> predicates = new ArrayList<>();
        for (FilterData filter : prefilter) {
            predicates.add(predicatBuilder(criteriaBuilder, from, filter));
        }
        //  LOG.info(String.format("....................................... %s ::: %s", prefilter.length, predicates.size()));
        if (predicates.size()>1) {
            leftCondition = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        } else if (predicates.size() == 1){
            leftCondition = predicates.get(0);
        }
        return leftCondition;
    }

    private <T extends ItemModel> Predicate predicatBuilder(CriteriaBuilder criteriaBuilder, Root<T> from, FilterData filter) {
        if (filter.getOperator().equals("ne")) {
            return criteriaBuilder.notEqual(getPath(from, filter.getField()), filter.getValue());
        } else if (filter.getOperator().equals("like")) {
            return criteriaBuilder.like((Expression<String>) getPath(from, filter.getField()),  (String)filter.getValue());
        }
        return criteriaBuilder.equal(getPath(from, filter.getField()), filter.getValue());
    }

    private  Path<String> getPath(Root root, String fieldname){
        Path<String> path = root;
        for(String part : fieldname.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }
}
