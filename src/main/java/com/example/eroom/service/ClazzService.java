package com.example.eroom.service;

import com.example.eroom.domain.Classes;
import com.example.eroom.repository.ClazzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClazzService {

    @Autowired final ClazzRepository repository;

    @Transactional
    public List<Classes> addClazz(Classes clazz){
        repository.save(clazz);
        return repository.findAll();
    }

//    @Transactional
//    public List<Classes> updateClazz(Classes clazz){
//
//    }

    @Transactional
    public List<Classes> deleteClazz(Classes clazz){
        repository.delete(clazz);
        return repository.findAll();
    }
}
