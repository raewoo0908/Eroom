package com.example.eroom.controller;

import com.example.eroom.domain.Student;
import com.example.eroom.dto.RequestStudentDTO;
import com.example.eroom.dto.ResponseDTO;
import com.example.eroom.dto.ResponseStudentDTO;
import com.example.eroom.repository.StudentRepository;
import com.example.eroom.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

    @Autowired private final StudentRepository studentRepository;
    @Autowired private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody RequestStudentDTO requestStudentDTO) {
        try {
            //Student 엔티티 생성
            Student student = RequestStudentDTO.toEntity(requestStudentDTO);

            //Student 추가
            List<Student> allStudents = studentService.addStudent(student);

            //responseStudentDTO List
            List<ResponseStudentDTO> dtos = allStudents.stream()
                    .map(std -> new ResponseStudentDTO(std))
                    .toList();

            //build reponseDTO
            ResponseDTO<ResponseStudentDTO> response = ResponseDTO.<ResponseStudentDTO>builder()
                    .data(dtos)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> allStudent = studentService.getAllStudent();

        List<ResponseStudentDTO> dtos = allStudent.stream()
                .map(std -> new ResponseStudentDTO(std))
                .toList();

        ResponseDTO<ResponseStudentDTO> response = ResponseDTO.<ResponseStudentDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/classes")
    public ResponseEntity<?> getClassesOfStudent(@RequestParam Long studentId) {
        List<Student> studentList = studentRepository.findWithAttendClassTeacher(studentId);

        List<ResponseStudentDTO> dtos = studentList.stream()
                .map(s -> new ResponseStudentDTO(s))
                .toList();

        ResponseDTO<ResponseStudentDTO> responseDTO = ResponseDTO.<ResponseStudentDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(responseDTO);
    }
}
