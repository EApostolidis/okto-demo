package com.example.oktodemo.model.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;
import com.example.oktodemo.service.WorkingDayAndTimeSlotService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/working-days", produces = APPLICATION_JSON_VALUE)
public class WorkingDayAndTimeSlotController implements WorkingDayAndTimeSlotApi {

  private final WorkingDayAndTimeSlotService workingDayAndTimeSlotService;

  public WorkingDayAndTimeSlotController(WorkingDayAndTimeSlotService workingDayAndTimeSlotService) {
    this.workingDayAndTimeSlotService = workingDayAndTimeSlotService;
  }

  @Override
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WorkingDayDto> updateWorkingDayAndTimeSlots(@RequestBody CreateOrUpdateDoctorWorkingDayRequest request) {
    WorkingDayDto response = workingDayAndTimeSlotService.updateWorkingDayAndTimeSlots(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
