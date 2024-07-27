package com.example.eroom.service;

import com.example.eroom.domain.*;
import com.example.eroom.exception.NoSuchAdminWorkException;
import com.example.eroom.exception.NoSuchStudentException;
import com.example.eroom.repository.StaffRepository;
import com.example.eroom.repository.StudentRepository;
import com.example.eroom.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;


    //행정업무 추가 메서드
    public Long addWork(Long staffId, Long StudentId, String detail) {
        // StaffId를 이용하여 Staff 찾아옴
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isEmpty()) {throw new NoSuchElementException("No such staff");}
        Staff findStaff = staff.get();

        //StudentId 이용하여 Student 찾아옴
        Optional<Student> student = studentRepository.findById(StudentId);
        if (student.isEmpty()) {throw new NoSuchStudentException("No such student");}
        Student findStudent = student.get();

        //WorkStudent 생성
        WorkStudent workStudent = WorkStudent.createWorkStudent(findStudent);

        //AdminWork 생성
        AdminWork adminWork = AdminWork.createAdminWork(findStaff, detail, workStudent);

        workRepository.save(adminWork);

        return adminWork.getId();
    }

    //업무 상태 변경 메서드 COMPLETED <-> ONGOING
    public void changeStatus(Long adminWorkId){
        Optional<AdminWork> adminWork = workRepository.findById(adminWorkId);
        if(adminWork.isEmpty()){throw new NoSuchAdminWorkException("No such admin work");}
        AdminWork findAdminWork = adminWork.get();

        if (findAdminWork.getStatus().equals("COMPLETED")){findAdminWork.setStatus(WorkStatusEnum.ONGOING);}
        else{
            findAdminWork.setStatus(WorkStatusEnum.COMPLETED);
        }
    }

    public void deleteWork(Long adminWorkId){
        Optional<AdminWork> adminWork = workRepository.findById(adminWorkId);
        if(adminWork.isEmpty()){throw new NoSuchAdminWorkException("No such admin work");}
        AdminWork findAdminWork = adminWork.get();

        workRepository.delete(findAdminWork);
    }

    //work 검색 기능

}
