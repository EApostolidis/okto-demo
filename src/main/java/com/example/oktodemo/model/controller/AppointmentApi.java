package com.example.oktodemo.model.controller;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.AppointmentDto;

public interface AppointmentApi {

  ResponseEntity<AppointmentDto> createAppointment(AppointmentDto appointmentDto);

}
