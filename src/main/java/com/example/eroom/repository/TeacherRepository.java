package com.example.eroom.repository;

import com.example.eroom.domain.Teacher;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {

    private final EntityManager em;

    public void save(Teacher teacher) {
        em.persist(teacher);
    }

    public Optional<Teacher> findById(Long id){
        Teacher teacher = em.find(Teacher.class, id);
        return Optional.ofNullable(teacher);
    }

    public List<Teacher> findAll(){
        String jpql = "SELECT t FROM Teacher t";
        return em.createQuery(jpql, Teacher.class).getResultList();
    }
}
