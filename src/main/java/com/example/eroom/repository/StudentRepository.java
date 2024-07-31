package com.example.eroom.repository;

import com.example.eroom.domain.Classes;
import com.example.eroom.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager em;

    public void save(Student student){
        em.persist(student);
    }

    public Optional<Student> findById(Long id){
        Student student = em.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    public Optional<Student> findByNameSchoolGrade(String name, String school, int grade){
        String jpql = "select s from Student s where s.schoolName = :name and s.schoolName = :school and s.grade = :grade";
        Student result = em.createQuery(jpql, Student.class)
                .setParameter("name", name)
                .setParameter("school", school)
                .setParameter("grade", grade)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    public List<Student> findWithAttendClassTeacher(Long id){
        String jpql = "select s from Student s" +
                " join fetch s.attendanceList a" +
                " join fetch a.classes c" +
                " join fetch c.teacher t" +
                " where s.id = :id";

        return em.createQuery(jpql, Student.class).setParameter("id", id).getResultList();
    }

    public List<Student> findAll(){
        String jpql = "SELECT s FROM Student s ";
        return em.createQuery(jpql, Student.class).getResultList();
    }
}
