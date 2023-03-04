package com.example.oktodemo.model.dto;

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
public class AppointmentDto {

  private LocalDate date;

  private LocalTime from;

  private LocalTime  to;

  private DoctorDto doctor;

  private PatientDto patient;
}
