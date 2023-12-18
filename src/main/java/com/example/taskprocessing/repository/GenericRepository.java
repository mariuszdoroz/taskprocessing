package com.example.taskprocessing.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BiFunction;

public abstract class GenericRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(GenericRepository.class);

    private final EntityManager em;
    private final Class<T> entityClass;

    public GenericRepository(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @Transactional
    public T create(T t) {
        em.persist(t);
        em.flush();
        logger.info("{} - saved in DB", entityClass.getSimpleName());
        return t;
    }

    @Transactional
    public T update(T t) {
        em.merge(t);
        logger.info("{} - updated in DB", entityClass.getSimpleName());
        return t;
    }

    public List<T> getAll() {
        logger.info("Get single {} from DB", entityClass.getSimpleName());
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        TypedQuery<T> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public T getSingleResultByPredicate(BiFunction<CriteriaBuilder, Root<T>, Predicate> predicateFunction) {
        logger.info("Get single {} from DB", entityClass.getSimpleName());
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        Predicate predicate = predicateFunction.apply(criteriaBuilder, root);
        if (predicate != null) {
            logger.info("Predicate apply");
            criteriaQuery.where(predicate);
        }

        TypedQuery<T> query = em.createQuery(criteriaQuery);
        try {
            logger.info("{} found in DB", entityClass.getSimpleName());
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("{} not found in DB", entityClass.getSimpleName());
            return null;
        }
    }
}
