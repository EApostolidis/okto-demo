package com.example.oktodemo.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

  private LocalDate date;

  private LocalDateTime from;

  private LocalDateTime to;
}
