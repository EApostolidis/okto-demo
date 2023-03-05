package com.example.oktodemo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oktodemo.model.dto.TimeSlotDto;
import com.example.oktodemo.model.dto.WorkingDayDto;

@SpringBootTest
class DoctorServiceTest {

  @Autowired
  DoctorService doctorService;

  @Test
  void test_successful_fetchDoctorWorkingHours() {
    Set<WorkingDayDto> responses = doctorService.fetchDoctorWorkingHours("doctorFirstName", "doctorLastName");
    WorkingDayDto workingDayDto = responses.stream().findFirst().get();
    TimeSlotDto timeSlotDto = workingDayDto.getTimeSlots().stream().findFirst().get();
    Assertions.assertEquals(LocalDate.of(2023, 03, 04),workingDayDto.getDate());
    Assertions.assertEquals(LocalTime.of( 00, 00), timeSlotDto.getFrom());
    Assertions.assertEquals(LocalTime.of( 11, 00), timeSlotDto.getTo());
  }
}