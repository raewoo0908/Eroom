package com.example.eroom.service;

import com.example.eroom.controller.ToEntity;
import com.example.eroom.domain.Classes;
import com.example.eroom.domain.SubjectEnum;
import com.example.eroom.domain.Teacher;
import com.example.eroom.dto.ClazzDTO;
import com.example.eroom.dto.TeacherDTO;
import com.example.eroom.exception.NoSuchTeacherException;
import com.example.eroom.repository.ClazzRepository;
import com.example.eroom.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherService {

    @Autowired private final TeacherRepository teacherRepository;
    @Autowired private final ClazzRepository clazzRepository;
    @Autowired private final ClazzService clazzService;
    @Autowired private final ToEntity toEntity;

    //선생님 추가 메서드
    @Transactional
    public List<Teacher> addTeacher(Teacher teacher) {
        validateDuplicatedTeacher(teacher);
        teacherRepository.save(teacher);
        return teacherRepository.findAll();
    }

    //선생님의 모든 정보 수정 메서드
    @Transactional
    public List<Teacher> updateTeacher(TeacherDTO dto) {
        Optional<Teacher> ogTeacher = teacherRepository.findById(dto.getId());

        if(ogTeacher.isPresent()) {
            Teacher newTeacher = ogTeacher.get();

            //name update
            newTeacher.setTeacherName(dto.getName());

            //mobile update
            newTeacher.setMobile(dto.getMobile());

            //subject update
            switch (dto.getSubject()){
                case "KOREAN": newTeacher.setSubject(SubjectEnum.KOREAN);
                case "ENGLISH": newTeacher.setSubject(SubjectEnum.ENGLISH);
                case "MATH": newTeacher.setSubject(SubjectEnum.MATH);
                case "SCIENCE": newTeacher.setSubject(SubjectEnum.SCIENCE);
            }

            //class update
            List<Classes> cList = new ArrayList<>();
            for (ClazzDTO cDTO : dto.getClazzDTOs()){
                Classes cEntity = toEntity.toClassesEntity(cDTO);
                cList.add(cEntity);
            }
            newTeacher.clearClassList();
            for (Classes c : cList) {
                newTeacher.addClasses(c);
            }

            teacherRepository.save(newTeacher);
        }else{
            throw new NoSuchTeacherException("Teacher not found");
        }

        return teacherRepository.findAll();
    }

    //선생님의 수업 SET 메서드.
    @Transactional
    public List<Teacher> setClassofThisTeacher(Long id, List<Classes> clazzList){
        Optional<Teacher> ogTeacher = teacherRepository.findById(id);

        if(ogTeacher.isPresent()) {
            Teacher newTeacher = ogTeacher.get();
            newTeacher.setClassesList(clazzList);

            teacherRepository.save(newTeacher);
        } else{
            throw new NoSuchTeacherException("Teacher not found");
        }
        return teacherRepository.findAll();
    }

    //과목별 선생님 조회 메서드
    public List<Teacher> findBySubject(String subject) {
        return teacherRepository.findBySubject(subject);
    }

    //선생님 삭제 메서드
    @Transactional
    public List<Teacher> deleteTeacher(Long id) {
        Optional<Teacher> OpTeacher = teacherRepository.findById(id);
        if (OpTeacher.isEmpty()) {throw new NoSuchTeacherException("Teacher not found");}
        Teacher teacher = OpTeacher.get();

        teacherRepository.delete(teacher);

        return teacherRepository.findAll();
    }





    //중복 검증 메서드
    private void validateDuplicatedTeacher(Teacher teacher) {
        Optional<Teacher> byMobile = teacherRepository.findByMobile(teacher);
        if (byMobile.isPresent()) {throw new IllegalStateException("This teacher has already been added");}
    }
}
