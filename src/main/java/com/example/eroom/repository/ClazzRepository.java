package com.example.eroom.repository;

import com.example.eroom.domain.Classes;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClazzRepository {

    private final EntityManager em;

    public void save(Classes clazz) {
        em.persist(clazz);
    }

    public Optional<Classes> findById(Long id) {
        Classes clazz = em.find(Classes.class, id);
        return Optional.ofNullable(clazz);
    }

    public List<Classes> findAll() {
        String jpql = "select c from Classes c";
        return em.createQuery(jpql, Classes.class).getResultList();
    }

    public void delete(Classes clazz) {
        em.remove(clazz);
    }
}
