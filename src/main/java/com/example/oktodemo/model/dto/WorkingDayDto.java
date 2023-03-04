package com.example.oktodemo.model.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkingDayDto {

  private LocalDate date;
  private Set<TimeSlotDto> timeSlots;
}
