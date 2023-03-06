package com.example.oktodemo.model.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request for the creation of a new appointment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAppointmentRequest {

  @NotNull
  private LocalDate date;
  @NotNull
  private LocalTime from;
  @NotNull
  private LocalTime to;
  @NotNull
  private String doctorFirstName;
  @NotNull
  private String doctorLastName;
  @NotNull
  private String patientFirstName;
  @NotNull
  private String patientLastName;
}
