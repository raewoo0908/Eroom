package com.example.eroom.service;

import com.example.eroom.domain.Staff;
import com.example.eroom.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository repository;

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
        Optional<Staff> byEmail = repository.findByEmail(staff.getStaffEmail());
        if (byEmail.isPresent()) {throw new IllegalStateException("This email is already used by another Staff.");}
    }
}
