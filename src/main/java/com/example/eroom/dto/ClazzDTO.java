package com.example.eroom.dto;

import com.example.eroom.domain.Classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzDTO {
    private Long id;
    private String classname;
    private String startTime;
    private String endTime;

    //Entity -> DTO
    public ClazzDTO(Classes c){
        this.id = c.getId();
        this.classname = c.getClassName();
        this.startTime = c.getStartTime().toString();
        this.endTime = c.getEndTime().toString();
    }


}
