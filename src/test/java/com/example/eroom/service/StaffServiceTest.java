//package com.example.eroom.service;
//
//import com.example.eroom.domain.Staff;
//import com.example.eroom.domain.StaffRoleEnum;
//import com.example.eroom.repository.StaffRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class StaffServiceTest {
//
//    @Autowired private StaffService staffService;
//
//    @Autowired private StaffRepository staffRepository;
//
//    @Test
//    public void addStaffTest() throws Exception{
//        //given
//        Staff staff1 = Staff.createStaff("raewoo", "raewoo0908@naver.com", StaffRoleEnum.STAFF);
//
//        //when
//        Long SavedStaffId = staffService.addStaff(staff1);
//
//        //then
//        Staff findStaff = staffRepository.findById(SavedStaffId).get();
//
//        Assertions.assertEquals(staff1.getId(), findStaff.getId());
//        Assertions.assertEquals(staff1.getStaffName(), findStaff.getStaffName());
//        Assertions.assertEquals(staff1.getEmail(), findStaff.getEmail());
//    }
//
//    @Test
//    public void duplicatedStaffTest() throws Exception{
//        //given
//        Staff staff1 = Staff.createStaff("raewoo", "raewoo0908@naver.com", StaffRoleEnum.STAFF);
//        Staff staff2 = Staff.createStaff("raewoo1", "raewoo0908@naver.com", StaffRoleEnum.STAFF);
//
//        //when
//        staffService.addStaff(staff1);
//
//        //then
//        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
//                () -> staffService.addStaff(staff2));
//        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("This email is already used by another Staff.");
//    }
//}
