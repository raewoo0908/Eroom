package com.example.eroom.service;

import com.example.eroom.controller.ToEntity;
import com.example.eroom.domain.*;
import com.example.eroom.dto.WorkDTO;
import com.example.eroom.exception.NoSuchAdminWorkException;
import com.example.eroom.exception.NoSuchStudentException;
import com.example.eroom.repository.StaffRepository;
import com.example.eroom.repository.StudentRepository;
import com.example.eroom.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final WorkRepository workRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;
    private final ToEntity toEntity;


    /*
    * 행정업무 생성
    * param: 새로 생성할 AdminWork
    * return: 신규 추가된 후의 업무 리스트
    * */
    @Transactional
    public List<AdminWork> createWork(AdminWork adminWork){
        workRepository.save(adminWork);
        return workRepository.findAll();
    }

    /*
    * 행정업무 수정 메서드
    * param: 수정할 AdminWork의 Id, 수정할 Staff, detail, 연관된 StudentId 리스트
    * return: 수정이 반영된 후의 전체 AdminWork 리스트
    * */
    @Transactional
    public List<AdminWork> updateWork(WorkDTO dto) {

        Optional<AdminWork> originalAdminWork = workRepository.findById(dto.getId());
        if (originalAdminWork.isPresent()) {
            final AdminWork newAdminWork = originalAdminWork.get();

            //detail update
            newAdminWork.setDetail(dto.getDetail());

            //staff update
            if(staffRepository.findById(dto.getStaffId()).isPresent()){
                Staff staff = staffRepository.findById(dto.getStaffId()).get();
                newAdminWork.setStaff(staff);
            }

            //WorkStudent update
            List<WorkStudent> workStudentList = new ArrayList<>();
            for (Long StudentId : dto.getStudentIdList()) {
                Optional<Student> student = studentRepository.findById(StudentId);
                if (student.isEmpty()) {throw new NoSuchStudentException("No such student");}
                Student findStudent = student.get();
                WorkStudent workStudent = WorkStudent.createWorkStudent(findStudent);
                workStudentList.add(workStudent);
            }
            newAdminWork.clearWorkStudentList();
            for (WorkStudent workStudent : workStudentList) {
                newAdminWork.addWorkStudent(workStudent);
            }

            //status update
            if(dto.getStatus().equals("COMPLETED")){
                newAdminWork.setStatus(WorkStatusEnum.COMPLETED);
            }else{
                newAdminWork.setStatus(WorkStatusEnum.ONGOING);
            }

            workRepository.save(newAdminWork);
        }

        return workRepository.findAll();
    }

    //업무 상태 변경 메서드 COMPLETED <-> ONGOING
    @Transactional
    public List<AdminWork> changeStatus(Long adminWorkId, String changeToThis){
        Optional<AdminWork> adminWork = workRepository.findById(adminWorkId);
        if(adminWork.isEmpty()){throw new NoSuchAdminWorkException("No such admin work");}
        AdminWork findAdminWork = adminWork.get();

        if (changeToThis.equals("COMPLETED")){
            findAdminWork.setStatus(WorkStatusEnum.COMPLETED);
        }else{
            findAdminWork.setStatus(WorkStatusEnum.ONGOING);
        }

        workRepository.save(findAdminWork);

        return workRepository.findAll();
    }

    @Transactional
    public List<AdminWork> deleteWork(Long adminWorkId){
        Optional<AdminWork> adminWork = workRepository.findById(adminWorkId);
        if(adminWork.isEmpty()){throw new NoSuchAdminWorkException("No such admin work");}
        AdminWork findAdminWork = adminWork.get();

        workRepository.delete(findAdminWork);

        return workRepository.findAll();
    }

    public List<AdminWork> findAllWork(){
        return workRepository.findAll();
    }

    //work 검색 기능

}
