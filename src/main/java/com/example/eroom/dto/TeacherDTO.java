package com.example.eroom.dto;

import com.example.eroom.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String name;
    private String mobile;
    private String subject;
    private List<ClazzDTO> clazzDTOs;

    //Entity -> DTO
    public TeacherDTO(Teacher t){
        this.id = t.getId();
        this.name = t.getTeacherName();
        this.mobile = t.getMobile();
        this.subject = t.getSubject().toString();
        this.clazzDTOs = t.getClassesList()
                .stream()
                .map(clazz -> new ClazzDTO(clazz))
                .toList();
    }
}
