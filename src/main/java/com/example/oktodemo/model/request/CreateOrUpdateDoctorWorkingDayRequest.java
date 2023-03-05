package com.example.oktodemo.model.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrUpdateDoctorWorkingDayRequest {

  private String doctorFirstName;

  private String doctorLastName;

  private LocalDate date;

  private LocalTime from;

  private LocalTime to;
}
