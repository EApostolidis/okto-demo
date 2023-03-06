package com.example.oktodemo.controller;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.request.CreateAppointmentRequest;

public interface AppointmentApi {

  /**
   * The api responsible for creating an appointment given as parameter
   * doctor's and patient's information as the date and the time
   * @param request contains all the needed to create the appointment {@link CreateAppointmentRequest}
   * @return information regarding the appointment {@link ResponseEntity<AppointmentDto>}
   */
  ResponseEntity<AppointmentDto> createAppointment(CreateAppointmentRequest request);

}
