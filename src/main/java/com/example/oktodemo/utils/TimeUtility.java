package com.example.oktodemo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.experimental.UtilityClass;

/**
 * Utility clas for checking dates and making validations
 */
@UtilityClass
public class TimeUtility {

  public static LocalDateTime createLocalDateTime(LocalDate date, LocalTime time) {
    return LocalDateTime.of(date, time);
  }

  public static boolean checkIfTimeIsInsidePeriod(LocalDateTime check, LocalDateTime from, LocalDateTime to) {
    return (check.equals(from) || check.isAfter(from)) && (check.isBefore(to) || check.equals(to));
  }

  public static void checkTimeValidity(LocalTime from, LocalTime to) {
    if (from.equals(to) || from.isAfter(to)) {
      throw new RuntimeException("The from value should be before to value");
    }
  }
}
