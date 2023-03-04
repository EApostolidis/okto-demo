package com.example.oktodemo.model.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrUpdateDoctorWorkingDayRequest {

  private String firstName;

  private String lastName;

  private LocalDate date;

  private LocalTime from;

  private LocalTime to;
}
