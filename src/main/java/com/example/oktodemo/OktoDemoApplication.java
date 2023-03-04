package com.example.oktodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.dto.DoctorDto;
import com.example.oktodemo.service.AppointmentService;
import com.example.oktodemo.service.DoctorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class OktoDemoApplication implements CommandLineRunner {

  @Autowired
  AppointmentService appointmentService;

  @Autowired
  DoctorService doctorService;

  public static void main(String[] args) {
    SpringApplication.run(OktoDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    log.info("run process");

    var result = appointmentService.fetchAppointments();
    AppointmentDto appointmentDto = AppointmentDto.builder().doctor(DoctorDto.builder().firstName("Vaggelis").lastName("Apostolidis").build()).build();
    var workingdays = appointmentService.createAppointment(appointmentDto);
    log.info("{end of process {}", result);

  }

}
