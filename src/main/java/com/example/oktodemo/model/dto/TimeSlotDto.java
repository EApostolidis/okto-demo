package com.example.oktodemo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDto {

  private LocalDateTime from;

  private LocalDateTime to;
}
