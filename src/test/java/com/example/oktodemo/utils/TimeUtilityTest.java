package com.example.oktodemo.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.oktodemo.utils.TimeUtility.checkIfTimeIsInsidePeriod;
import static com.example.oktodemo.utils.TimeUtility.checkTimeValidity;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeUtilityTest {

  @Test
  void test_checkIfTimeIsInsidePeriod() {
    Assertions.assertEquals(true, checkIfTimeIsInsidePeriod(LocalDateTime.of(2000,01,01,01,00),
        LocalDateTime.of(2000,01,01,00,00),
        LocalDateTime.of(2000,01,01,02,00)));
    Assertions.assertEquals(false, checkIfTimeIsInsidePeriod(LocalDateTime.of(2000,01,01,10,00),
        LocalDateTime.of(2000,01,01,00,00),
        LocalDateTime.of(2000,01,01,02,00)));
    Assertions.assertEquals(true, checkIfTimeIsInsidePeriod(LocalDateTime.of(2000,01,01,00,00),
        LocalDateTime.of(2000,01,01,00,00),
        LocalDateTime.of(2000,01,01,02,00)));
    Assertions.assertEquals(true, checkIfTimeIsInsidePeriod(LocalDateTime.of(2000,01,01,02,00),
        LocalDateTime.of(2000,01,01,00,00),
        LocalDateTime.of(2000,01,01,02,00)));
  }

  @Test
  void test_checkTimeValidity() {
    Throwable exception = assertThrows(RuntimeException.class, () -> checkTimeValidity(LocalTime.of(11,00), LocalTime.of(10,00)));
    Assertions.assertEquals("The from value should be before to value", exception.getMessage());
  }
}