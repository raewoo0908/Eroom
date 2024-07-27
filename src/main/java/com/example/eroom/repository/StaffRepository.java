package com.example.eroom.repository;

import com.example.eroom.domain.Staff;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StaffRepository {

    private final EntityManager em;

    public void save(Staff staff) {
        em.persist(staff);
    }

    public Optional<Staff> findById(Long id){
        Staff staff = em.find(Staff.class, id);
        return Optional.ofNullable(staff);
    }

    public List<Staff> findAll(){
        String jpql = "SELECT s FROM Staff s";
        return em.createQuery(jpql, Staff.class).getResultList();
    }

    public Optional<Staff> findByEmail(String email){
        String jpql = "SELECT s FROM Staff s WHERE s.staffEmail = :email";
        List<Staff> findStaffs = em.createQuery(jpql, Staff.class).setParameter("email", email).getResultList();
        return findStaffs.stream().findAny();
    }

}
