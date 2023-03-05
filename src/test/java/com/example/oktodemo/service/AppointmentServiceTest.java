package com.example.oktodemo.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.request.CreateAppointmentRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AppointmentServiceTest {

  @Autowired
  private AppointmentService appointmentService;

  @Test
  public void test_successful_createAppointment() {
    CreateAppointmentRequest request = CreateAppointmentRequest.builder()
        .doctorFirstName("doctorFirstName")
        .doctorLastName("doctorLastName")
        .patientFirstName("patientFirstName")
        .patientLastName("patientLastName")
        .date(LocalDate.of(2023, 03, 04))
        .from(LocalTime.of(02, 00))
        .to(LocalTime.of(02, 30))
        .build();

    AppointmentDto response = appointmentService.createAppointment(request);
    Assertions.assertEquals(request.getDoctorFirstName(), response.getDoctor().getFirstName());
    Assertions.assertEquals(request.getDoctorLastName(), response.getDoctor().getLastName());
    Assertions.assertEquals(request.getDate(), response.getDate());
    Assertions.assertEquals(request.getFrom(), response.getFrom());
    Assertions.assertEquals(request.getTo(), response.getTo());
    Assertions.assertEquals(request.getPatientFirstName(), response.getPatient().getFirstName());
    Assertions.assertEquals(request.getPatientLastName(), response.getPatient().getLastName());
  }

  @Test
  public void test_doctor_not_found_createAppointment() {
    CreateAppointmentRequest request = CreateAppointmentRequest.builder()
        .doctorFirstName("doctorFirstName1")
        .doctorLastName("doctorLastName")
        .patientFirstName("patientFirstName")
        .patientLastName("patientLastName")
        .date(LocalDate.of(2023, 03, 04))
        .from(LocalTime.of(02, 00))
        .to(LocalTime.of(02, 30))
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> appointmentService.createAppointment(request));
    Assertions.assertEquals("There is no doctor with the provided name", exception.getMessage());
  }

  @Test
  public void test_patient_not_found_createAppointment() {
    CreateAppointmentRequest request = CreateAppointmentRequest.builder()
        .doctorFirstName("doctorFirstName")
        .doctorLastName("doctorLastName")
        .patientFirstName("patientFirstName2")
        .patientLastName("patientLastName2")
        .date(LocalDate.of(2023, 03, 04))
        .from(LocalTime.of(02, 00))
        .to(LocalTime.of(02, 30))
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> appointmentService.createAppointment(request));
    Assertions.assertEquals("There is no patient with the provided name", exception.getMessage());
  }

  @Test
  public void test_not_available_time_createAppointment() {
    CreateAppointmentRequest request = CreateAppointmentRequest.builder()
        .doctorFirstName("doctorFirstName")
        .doctorLastName("doctorLastName")
        .patientFirstName("patientFirstName")
        .patientLastName("patientLastName")
        .date(LocalDate.of(2023, 03, 05))
        .from(LocalTime.of(02, 00))
        .to(LocalTime.of(02, 30))
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> appointmentService.createAppointment(request));
    Assertions.assertEquals("There is no available time for appointment", exception.getMessage());
  }

  @Test
  public void test_appointment_exists_createAppointment() {
    CreateAppointmentRequest request = CreateAppointmentRequest.builder()
        .doctorFirstName("doctorFirstName")
        .doctorLastName("doctorLastName")
        .patientFirstName("patientFirstName")
        .patientLastName("patientLastName")
        .date(LocalDate.of(2023, 03, 04))
        .from(LocalTime.of(00, 10))
        .to(LocalTime.of(00, 30))
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> appointmentService.createAppointment(request));
    Assertions.assertEquals("There is already booked appointment at that day and hour", exception.getMessage());
  }
}