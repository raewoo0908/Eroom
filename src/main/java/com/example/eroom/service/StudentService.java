package com.example.eroom.service;

import com.example.eroom.domain.Classes;
import com.example.eroom.domain.Payment;
import com.example.eroom.domain.Student;
import com.example.eroom.exception.NoSuchStudentException;
import com.example.eroom.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    /*
    * 학생 추가 메서드
    * param: Student
    * return: 저장된 학생의 id
    * */
    @Transactional
    public List<Student> addStudent(Student student) {
        if (student.getAttendanceList() == null){
            student.setAttendanceList(new ArrayList<>());
        }
        studentRepository.save(student);
        return studentRepository.findAll();
    }

    /*
    * 학생 이름+학교+학년으로 검색 메서드
    * param: String name, String school, int grade
    * return: Student
    * */
    public Student getStudentByNameSchoolGrade(String name, String school, int grade){
        Optional<Student> OptionalStudent = studentRepository.findByNameSchoolGrade(name, school, grade);
        if (OptionalStudent.isPresent()){
            return  OptionalStudent.get();
        } else{
            throw new NoSuchStudentException();
        }
    }

    /*
    * 학생 id로 학생 검색 메서드
    * param: Long id of student
    * return: Student*/
    public Student getStudentById(Long id){
        Optional<Student> OptionalStudent = studentRepository.findById(id);
        if (OptionalStudent.isPresent()){
            return  OptionalStudent.get();
        }else{
            throw new NoSuchStudentException();
        }
    }

    /*
    * 모든 학생 목록 메서드
    * param: void
    * return List<Student>
    * */
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    /*
     * 해당 학생이 수강중인 수업 보여주기 메서드
     * param: Long id of Student
     * return: List<Class>
     * */



    /*
    * 해당 학생이 수납해야 할 금액 보여주기 메서드
    * param: Long id of Student
    * return: List<Payment>
    * */






}
