package com.example.oktodemo.controller;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;

public interface WorkingDayAndTimeSlotApi {

  /**
   * The api responsible for updating doctor's working days and timeslots.
   * @param request contains all the information needed to add new working days and timeslot to the doctor {@link CreateOrUpdateDoctorWorkingDayRequest}
   * @return the created working days {@link ResponseEntity<WorkingDayDto>}
   */
  ResponseEntity<WorkingDayDto> updateWorkingDayAndTimeSlots(CreateOrUpdateDoctorWorkingDayRequest request);

}
