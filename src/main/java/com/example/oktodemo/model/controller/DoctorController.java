package com.example.oktodemo.model.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.service.DoctorService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/doctors", produces = APPLICATION_JSON_VALUE)
public class DoctorController implements DoctorApi {
  private final DoctorService doctorService;

  public DoctorController(DoctorService doctorService) {
    this.doctorService = doctorService;
  }

  @Override
  @GetMapping
  public ResponseEntity<Set<WorkingDayDto>> getDoctorWorkingDayAndTimeslots(@RequestParam String firstName, @RequestParam String lastName) {
    Set<WorkingDayDto> response = doctorService.fetchDoctorWorkingHours(firstName, lastName);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
