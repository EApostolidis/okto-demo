package com.example.oktodemo.model.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDto {

  private LocalTime from;

  private LocalTime to;
}
