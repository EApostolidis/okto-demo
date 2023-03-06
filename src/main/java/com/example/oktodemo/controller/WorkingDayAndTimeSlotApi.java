package com.example.oktodemo.controller;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;

public interface WorkingDayAndTimeSlotApi {
  ResponseEntity<WorkingDayDto> updateWorkingDayAndTimeSlots(CreateOrUpdateDoctorWorkingDayRequest request);

}
