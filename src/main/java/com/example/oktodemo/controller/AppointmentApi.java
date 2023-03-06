package com.example.oktodemo.controller;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.request.CreateAppointmentRequest;

public interface AppointmentApi {

  ResponseEntity<AppointmentDto> createAppointment(CreateAppointmentRequest request);

}
