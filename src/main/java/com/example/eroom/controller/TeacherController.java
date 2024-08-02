package com.example.eroom.controller;

import com.example.eroom.domain.Teacher;
import com.example.eroom.dto.ResponseDTO;
import com.example.eroom.dto.TeacherDTO;
import com.example.eroom.dto.WorkDTO;
import com.example.eroom.repository.TeacherRepository;
import com.example.eroom.service.ClazzService;
import com.example.eroom.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final ClazzService clazzService;
    private final ToEntity toEntity;
    private final TeacherRepository teacherRepository;

    @PostMapping
    public ResponseEntity<?> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        try{
            Teacher teacher = toEntity.toTeacherEntity(teacherDTO);
            List<Teacher> allTeachers = teacherService.addTeacher(teacher);

            List<TeacherDTO> dtos = allTeachers.stream()
                    .map(t -> new TeacherDTO(t))
                    .toList();

            ResponseDTO<TeacherDTO> responseDTO = ResponseDTO.<TeacherDTO>builder()
                    .data(dtos)
                    .build();

            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        try{
            List<Teacher> allTeachers = teacherRepository.findAll();

            List<TeacherDTO> dtos = allTeachers.stream()
                    .map(t -> new TeacherDTO(t))
                    .toList();

            ResponseDTO<TeacherDTO> responseDTO = ResponseDTO.<TeacherDTO>builder().data(dtos).build();

            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        try{
            List<Teacher> allTeachers = teacherService.updateTeacher(teacherDTO);

            List<TeacherDTO> dtos = allTeachers.stream()
                    .map(t -> new TeacherDTO(t))
                    .toList();

            ResponseDTO<TeacherDTO> responseDTO = ResponseDTO.<TeacherDTO>builder().data(dtos).build();

            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            List<Teacher> allTeachers = teacherService.deleteTeacher(teacherDTO.getId());

            List<TeacherDTO> dtos = allTeachers.stream()
                    .map(t -> new TeacherDTO(t))
                    .toList();

            ResponseDTO<TeacherDTO> responseDTO = ResponseDTO.<TeacherDTO>builder().data(dtos).build();

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<WorkDTO> response = ResponseDTO.<WorkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


}
