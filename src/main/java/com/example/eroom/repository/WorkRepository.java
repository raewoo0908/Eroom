package com.example.eroom.repository;

import com.example.eroom.domain.AdminWork;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkRepository {

    private final EntityManager em;

    public void save(AdminWork work) {
        em.persist(work);
    }

    public Optional<AdminWork> findById(Long id) {
        AdminWork work = em.find(AdminWork.class, id);
        return Optional.ofNullable(work);
    }

    public void delete(AdminWork work) {
        em.remove(work);
    }

    public List<AdminWork> findAll() {
        return em.createQuery("select w from AdminWork w", AdminWork.class).getResultList();
    }

    /*업무 검색 기능
    * 스태프별로 검색
    * 업무 학부모 or 학생 이름 + 업무 세부내용 + 전화번호 like로 검색 -> 관련된 row 모두 리턴
    * */
}
