package com.example.oktodemo.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.example.oktodemo.model.dto.WorkingDayDto;

public interface DoctorApi {

  ResponseEntity<Set<WorkingDayDto>> getDoctorWorkingDayAndTimeslots(String firstName, String lastName);

}
