package com.example.oktodemo.model.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAppointmentRequest {

  private LocalDate date;

  private LocalTime from;

  private LocalTime to;

  private String doctorFirstName;

  private String doctorLastName;

  private String patientFirstName;

  private String patientLastName;
}
