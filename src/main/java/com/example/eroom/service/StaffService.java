package com.example.eroom.service;

import com.example.eroom.domain.Staff;
import com.example.eroom.exception.NoSuchStaffException;
import com.example.eroom.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffRepository repository;

    @Transactional
    public Long addStaff(Staff staff) {
        validateDuplicatedStaff(staff);
        repository.save(staff);
        return staff.getId();
    }

    public List<Staff> findAllStaff(){
        return repository.findAll();
    }

    public Optional<Staff> findStaffById(Long id){
        return repository.findById(id);
    }

    private void validateDuplicatedStaff(Staff staff) {
        Optional<Staff> byEmail = repository.findByEmail(staff.getEmail());
        if (byEmail.isPresent()) {throw new IllegalStateException("This email is already used by another Staff.");}
    }

    public Optional<Staff> findStaffByCredentials(final String email, final String password) {
        if (repository.findByEmailAndPassword(email, password).isPresent()) {
            return repository.findByEmailAndPassword(email, password);
        }else{
            throw new NoSuchStaffException("This staff does not exist.");
        }
    }
}
