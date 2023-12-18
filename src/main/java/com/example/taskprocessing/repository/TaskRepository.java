package com.example.taskprocessing.repository;

import com.example.taskprocessing.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.function.BiFunction;

@Repository
public class TaskRepository extends GenericRepository<Task> {

    public TaskRepository(EntityManager em) {
        super(em, Task.class);
    }

    public Task getById(Long id) {
        BiFunction<CriteriaBuilder, Root<Task>, Predicate> predicateFunction = (criteriaBuilder, root) ->
                        criteriaBuilder.equal(root.get("id"), id);
        return getSingleResultByPredicate(predicateFunction);
    }
}