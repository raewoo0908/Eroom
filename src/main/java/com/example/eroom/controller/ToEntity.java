package com.example.eroom.controller;

import com.example.eroom.domain.*;
import com.example.eroom.dto.ClazzDTO;
import com.example.eroom.dto.TeacherDTO;
import com.example.eroom.dto.WorkDTO;
import com.example.eroom.exception.NoSuchStudentException;
import com.example.eroom.repository.StaffRepository;
import com.example.eroom.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ToEntity {

    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;

    public AdminWork toAdminWorkEntity(WorkDTO dto){
        //staff injection
        Optional<Staff> staff = staffRepository.findById(dto.getStaffId());
        if (staff.isEmpty()) {throw new NoSuchElementException("No such staff");}
        Staff findStaff = staff.get();

        //WorkStudent injection
        List<WorkStudent> workStudentList = new ArrayList<>();
        for (Long StudentId : dto.getStudentIdList()) {
            Optional<Student> student = studentRepository.findById(StudentId);
            if (student.isEmpty()) {throw new NoSuchStudentException("No such student");}
            Student findStudent = student.get();
            WorkStudent workStudent = WorkStudent.createWorkStudent(findStudent);
            workStudentList.add(workStudent);
        }

        return AdminWork.createAdminWork(findStaff, dto.getDetail(), workStudentList, dto.getStatus());
    }

    public Classes toClassesEntity(ClazzDTO dto){
        LocalTime startTime = LocalTime.parse(dto.getStartTime(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime endTime = LocalTime.parse(dto.getEndTime(), DateTimeFormatter.ofPattern("HH:mm"));

        return Classes.createClasses(dto.getClassname(), startTime, endTime);
    }

    public Teacher toTeacherEntity(TeacherDTO dto){
        List<Classes> classesList = dto.getClazzDTOs()
                .stream()
                .map(cDTO -> toClassesEntity(cDTO))
                .toList();

        return Teacher.createTeacher(dto.getName(), dto.getMobile(), dto.getSubject(), classesList);
    }
}
