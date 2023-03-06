package com.example.oktodemo.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.WorkingDayDto;

public interface DoctorApi {

  /**
   * The api responsible for retrieving doctor's working days and timeslots
   * @param firstName the doctor's first name
   * @param lastName the doctor's last name
   * @return a set of {@link Set<WorkingDayDto>}
   */
  ResponseEntity<Set<WorkingDayDto>> getDoctorWorkingDayAndTimeslots(String firstName, String lastName);

}
