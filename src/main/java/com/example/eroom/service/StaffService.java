package com.example.eroom.service;

import com.example.eroom.domain.Staff;
import com.example.eroom.exception.NoSuchStaffException;
import com.example.eroom.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffRepository repository;

    @Transactional
    public Staff addStaff(Staff staff) {
        validateDuplicatedStaff(staff);
        repository.save(staff);
        return staff;
    }

    public List<Staff> findAllStaff(){
        return repository.findAll();
    }

    public Staff findStaffById(Long id){
        Optional<Staff> optionalStaff = repository.findById(id);

        if (optionalStaff.isPresent()){
            return optionalStaff.get();
        } else{
            throw new NoSuchStaffException();
        }
    }

    private void validateDuplicatedStaff(Staff staff) {
        Optional<Staff> byEmail = repository.findByEmail(staff.getEmail());
        if (byEmail.isPresent()) {throw new IllegalStateException("This email is already used by another Staff.");}
    }

    public Staff findStaffByCredentials(final String email, final String password) {
        Optional<Staff> optionalStaff = repository.findByEmailAndPassword(email, password);

        if (optionalStaff.isPresent()) {
            return optionalStaff.get();
        }else{
            throw new NoSuchStaffException("This staff does not exist.");
        }
    }
}
