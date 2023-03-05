package com.example.oktodemo.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oktodemo.model.dto.TimeSlotDto;
import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;

@SpringBootTest
class WorkingDayAndTimeSlotServiceTest {

  @Autowired
  WorkingDayAndTimeSlotService workingDayAndTimeSlotService;

  @Test
  public void successful_updateWorkingDayAndTimeSlots() {
    CreateOrUpdateDoctorWorkingDayRequest request = CreateOrUpdateDoctorWorkingDayRequest.builder()
        .doctorFirstName("doctorFirstName")
        .doctorLastName("doctorLastName")
        .from(LocalTime.of(01,00))
        .to(LocalTime.of(02,00))
        .date(LocalDate.of(2023, 03, 05))
        .build();
    WorkingDayDto response = workingDayAndTimeSlotService.updateWorkingDayAndTimeSlots(request);
    TimeSlotDto timeSlotDto = response.getTimeSlots().stream().findFirst().get();
    Assertions.assertEquals(request.getDate(), response.getDate());
    Assertions.assertEquals(request.getFrom(), timeSlotDto.getFrom());
    Assertions.assertEquals(request.getTo(), timeSlotDto.getTo());
  }
}