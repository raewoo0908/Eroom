package com.example.eroom.repository;

import com.example.eroom.domain.Payment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    private final EntityManager em;

    public void save(Payment payment) {
        em.persist(payment);
    }

    public Optional<Payment> findById(Long id) {
        Payment payment = em.find(Payment.class, id);
        return Optional.ofNullable(payment);
    }

    public List<Payment> findAll() {
        String jpql = "SELECT p FROM Payment p";
        return em.createQuery(jpql, Payment.class).getResultList();
    }
}
