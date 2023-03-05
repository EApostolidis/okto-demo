package com.example.oktodemo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oktodemo.model.entity.PatientEntity;

@SpringBootTest
class PatientServiceTest {

  @Autowired
  PatientService patientService;

  @Test
  void successful_fetchPatientByFirstNameAndLastName() {
    PatientEntity patientEntity = patientService.fetchPatientByFirstNameAndLastName("patientFirstName", "patientLastName");
    Assertions.assertEquals("patientFirstName", patientEntity.getFirstName());
    Assertions.assertEquals("patientLastName", patientEntity.getLastName());
  }
}