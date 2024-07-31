package com.example.eroom.dto;

import com.example.eroom.domain.AbsenceReasonEnum;
import com.example.eroom.domain.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private String className;
    private String teacherName;
    private LocalDate classDate;
    private boolean isAttend;
    private AbsenceReasonEnum absenceReason;

    public AttendanceDTO(Attendance attendance) {
        this.className = attendance.getClasses().getClassName();
        this.teacherName = attendance.getClasses().getTeacher().getTeacherName();
        this.classDate = attendance.getClassDate();
        this.isAttend = attendance.isAttend();
        this.absenceReason = attendance.getAbsenceReason();
    }
}
