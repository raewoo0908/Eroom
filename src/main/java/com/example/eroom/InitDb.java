package com.example.eroom;

import com.example.eroom.domain.*;
import com.example.eroom.service.WorkService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {
        private final EntityManager em;
        StaffRoleEnum staffRole = StaffRoleEnum.STAFF;
        StaffRoleEnum admin = StaffRoleEnum.ADMIN;

        public void dbInit2(){
            Staff stf = Staff.createStaff("raewoo", "raewoo0908@naver.com", staffRole);
            em.persist(stf);

            Student std1 = Student.createStudent("강솔", "경안고등학교", 3, "010-0000-0000", "010-1111-1111", "010-2222-2222");
            Student std2 = Student.createStudent("김은채", "고잔고등학교", 3, "010-1234-1234", "010-1123-1232", "010-1023-1239");
            em.persist(std1);
            em.persist(std2);

            WorkStudent ws1 = WorkStudent.createWorkStudent(std1);
            WorkStudent ws2 = WorkStudent.createWorkStudent(std2);
            em.persist(ws1);
            em.persist(ws2);

            List<WorkStudent> workStudentList = List.of(ws1, ws2);

            AdminWork adminWork = AdminWork.createAdminWork(stf, "initWork", workStudentList, "ONGOING");
            em.persist(adminWork);


            Classes c1 = Classes.createClasses("개별맞춤C", LocalTime.of(10,0), LocalTime.of(13,0));
            Classes c2 = Classes.createClasses("정규 확통", LocalTime.of(8,0), LocalTime.of(10,0));
            em.persist(c1);
            em.persist(c2);

            List<Classes> cList = List.of(c1, c2);

            Teacher t = Teacher.createTeacher("강민재", "010-1234-4545", "MATH", cList);
            em.persist(t);

            Attendance att1 = Attendance.createAttendance(std1, c1, null);
            Attendance att2 = Attendance.createAttendance(std2, c2, null);

            em.persist(att1);
            em.persist(att2);
        }


        public void dbInit(){
            Staff staff = createStaff("raewoo", "raewoo0908@naver.com", staffRole);
            Student std1 = createStudent("Kang sol", "kyeonAn", 3, "010-1111-1111", "010-1111-2222", "010-1111-3333");
            Student std2 = createStudent("Kim Eunchae", "goJan", 3, "010-1111-1112", "010-1111-2223", null);
            List<Student> studentList = List.of(std1, std2);
            WorkStudent ws1 = WorkStudent.createWorkStudent(std1);
            WorkStudent ws2 = WorkStudent.createWorkStudent(std2);
            List<WorkStudent> workStudentList = List.of(ws1, ws2);
            AdminWork work = createAdminWork(staff, "work1", workStudentList, "ONGOING");

            List<Classes> classesList = new ArrayList<>();

            Teacher teacher = createTeacher("minjae", SubjectEnum.MATH, classesList);

            Classes clazz = createClasses("개별맞춤C", LocalTime.of(10, 0), LocalTime.of(12,0));

            Attendance att1 = Attendance.createAttendance(std1, clazz, null);
            Attendance att2 = Attendance.createAttendance(std2, clazz, null);

            em.persist(att1);
            em.persist(att2);
        }

        private Staff createStaff(String name, String email, StaffRoleEnum role){
            Staff staff = Staff.createStaff(name, email, role);
            em.persist(staff);
            return staff;
        }

        private AdminWork createAdminWork(Staff staff, String detail, List<WorkStudent> workStudentList, String status){
            AdminWork adminWork = AdminWork.createAdminWork(staff, detail, workStudentList, status);
            em.persist(adminWork);
            return adminWork;
        }

        private Student createStudent(String name, String schoolName, int grade, String stdMob, String momMob, String dadMob){
            Student student = Student.builder()
                    .studentName(name)
                    .schoolName(schoolName)
                    .studentMobile(stdMob)
                    .momMobile(momMob)
                    .dadMobile(dadMob)
                    .grade(grade)
                    .build();
            em.persist(student);
            return student;
        }

        private Classes createClasses(String name, LocalTime sTime, LocalTime eTime){
            Classes clazz = Classes.createClasses(name, sTime, eTime);
            em.persist(clazz);
            return clazz;
        }

        private Teacher createTeacher(String n, SubjectEnum sub, List<Classes> classesList){
            Teacher teacher = Teacher.builder()
                    .teacherName(n)
                    .subject(sub)
                    .classesList(classesList)
                    .build();
            em.persist(teacher);
            return teacher;
        }
    }
}
