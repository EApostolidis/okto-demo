package com.example.oktodemo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.dto.DoctorDto;
import com.example.oktodemo.model.dto.PatientDto;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;
import com.example.oktodemo.service.AppointmentService;
import com.example.oktodemo.service.DoctorService;
import com.example.oktodemo.service.WorkingDayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class OktoDemoApplication implements CommandLineRunner {

  @Autowired
  AppointmentService appointmentService;

  @Autowired
  DoctorService doctorService;

  @Autowired
  WorkingDayService workingDayService;

  public static void main(String[] args) {
    SpringApplication.run(OktoDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    log.info("run process");



    AppointmentDto appointmentDto = AppointmentDto.builder()
        .date(LocalDate.of(2023, 03,04))
        .from(LocalTime.of(03,00))
        .to(LocalTime.of(04, 00))
        .doctor(DoctorDto.builder().firstName("Vaggelis").lastName("Apostolidis").build())
        .patient(PatientDto.builder().firstName("Rigas").lastName("Paparigas").build())
        .build();
    var workingdays = appointmentService.createAppointment(appointmentDto);

    var request = CreateOrUpdateDoctorWorkingDayRequest.builder()
        .firstName("Vaggelis")
        .lastName("Apostolidis")
        .date(LocalDate.of(2023, 03,9))
        .from(LocalTime.of(14,00))
        .to(LocalTime.of(15, 00))
        .build();

    var result = workingDayService.updateWorkingDayAndTimeSlots(request);
//    var workingdays2 = appointmentService.createAppointment(appointmentDto);
    log.info("{end of process {}", result);

  }

}
