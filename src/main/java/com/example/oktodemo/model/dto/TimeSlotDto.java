package com.example.oktodemo.model.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSlotDto {

  private LocalTime from;

  private LocalTime to;
}
