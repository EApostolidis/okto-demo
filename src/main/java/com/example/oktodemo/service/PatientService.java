package com.example.oktodemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.entity.PatientEntity;
import com.example.oktodemo.repository.PatientEntityRepository;

@Service
public class PatientService {

  private final PatientEntityRepository patientEntityRepository;

  public PatientService(PatientEntityRepository patientEntityRepository) {
    this.patientEntityRepository = patientEntityRepository;
  }

  public PatientEntity fetchPatientByFirstNameAndLastName(String firstName, String lastName) {
    Optional<PatientEntity> patientEntity = patientEntityRepository.findPatientEntitiesByFirstNameAndLastName(firstName, lastName);
    if (patientEntity.isEmpty()) {
      throw new RuntimeException("There is no patient with the provided name");
    }
    return patientEntity.get();
  }
}
