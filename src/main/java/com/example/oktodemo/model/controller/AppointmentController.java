package com.example.oktodemo.model.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.service.AppointmentService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/appointments", produces = APPLICATION_JSON_VALUE)
public class AppointmentController implements AppointmentApi {

  private final AppointmentService appointmentService;

  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @Override
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto) {
    AppointmentDto response = appointmentService.createAppointment(appointmentDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
