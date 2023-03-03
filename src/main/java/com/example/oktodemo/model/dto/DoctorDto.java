package com.example.oktodemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

  private String firstName;

  private String lastName;
}
