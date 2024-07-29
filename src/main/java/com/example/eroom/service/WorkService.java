package com.example.eroom.service;

import com.example.eroom.domain.*;
import com.example.eroom.exception.NoSuchAdminWorkException;
import com.example.eroom.exception.NoSuchStaffException;
import com.example.eroom.exception.NoSuchStudentException;
import com.example.eroom.repository.StaffRepository;
import com.example.eroom.repository.StudentRepository;
import com.example.eroom.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;


    /*
    * 행정업무 생성
    * param: 업무 기록 Staff id, 세부사항, 관련된 Student id list
    * return: 신규 추가된 업무의 Id
    * */
    public List<AdminWork> createWork(Long staffId, String detail, List<Long> StudentIds) {
        List<WorkStudent> workStudentList = new ArrayList<>();

        // StaffId를 이용하여 Staff 찾아옴
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isEmpty()) {throw new NoSuchElementException("No such staff");}
        Staff findStaff = staff.get();

        //StudentId 이용하여 Student 찾아오고, workStudent 객체를 만들어서 리스트에 저장함.
        for (Long StudentId : StudentIds) {
            Optional<Student> student = studentRepository.findById(StudentId);
            if (student.isEmpty()) {throw new NoSuchStudentException("No such student");}
            Student findStudent = student.get();
            WorkStudent workStudent = WorkStudent.createWorkStudent(findStudent);
            workStudentList.add(workStudent);
        }

        //AdminWork 생성
        AdminWork adminWork = AdminWork.createAdminWork(findStaff, detail, workStudentList);

        //AdminWork 저장
        workRepository.save(adminWork);

        return workRepository.findAll();
    }

    /*
    * 행정업무 수정 메서드
    * param: 수정할 AdminWork의 Id, 수정할 Staff, detail, 연관된 StudentId 리스트
    * return: 수정이 반영된 후의 전체 AdminWork 리스트
    * */
    public List<AdminWork> updateWork(Long workId, Long staffId, String detail, List<Long> StudentIds) {

        final Optional<AdminWork> originalAdminWork = workRepository.findById(workId);

        if (originalAdminWork.isPresent()){
            //AdminWork 찾아오기
            AdminWork adminWork = originalAdminWork.get();

            //Detail 덮어쓰기
            adminWork.setDetail(detail);

            //Staff 찾아오고 덮어쓰기
            if(staffRepository.findById(staffId).isPresent()){
                Staff staff = staffRepository.findById(staffId).get();
                adminWork.setStaff(staff);
            }

            //WorkStudent 만들고 덮어쓰기
            List<WorkStudent> workStudentList = new ArrayList<>();
            for (Long StudentId : StudentIds) {
                Optional<Student> student = studentRepository.findById(StudentId);
                if (student.isEmpty()) {throw new NoSuchStudentException("No such student");}
                Student findStudent = student.get();
                WorkStudent workStudent = WorkStudent.createWorkStudent(findStudent);
                workStudentList.add(workStudent);
            }
            adminWork.setWorkStudentList(workStudentList);

            workRepository.save(adminWork);
        }

        return workRepository.findAll();
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
