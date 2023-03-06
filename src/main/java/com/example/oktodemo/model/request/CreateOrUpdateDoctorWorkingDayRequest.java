package com.example.oktodemo.model.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request for the creation of new timeslots or working days
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrUpdateDoctorWorkingDayRequest {
  @NotNull
  private String doctorFirstName;
  @NotNull
  private String doctorLastName;
  @NotNull
  private LocalDate date;
  @NotNull
  private LocalTime from;
  @NotNull
  private LocalTime to;
}
