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

    public Optional<Teacher> findByMobile(Teacher teacher) {
        String jpql = "SELECT t FROM Teacher t WHERE t.mobile = :mobile";
        Teacher findTeacher = em.createQuery(jpql, Teacher.class)
                            .setParameter("mobile", teacher.getMobile())
                            .getSingleResult();
        return Optional.ofNullable(findTeacher);
    }

    public List<Teacher> findBySubject(String subject) {
        String jpql = "SELECT t FROM Teacher t WHERE t.subject = :subject";

        return em.createQuery(jpql, Teacher.class)
                .setParameter("subject", subject)
                .getResultList();
    }

    public void delete(Teacher teacher) {
        em.remove(teacher);
    }
}
