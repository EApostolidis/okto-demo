package com.example.oktodemo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.PatientDto;
import com.example.oktodemo.model.entity.PatientEntity;
import com.example.oktodemo.repository.PatientEntityRepository;

@Service
public class PatientService {

  private final PatientEntityRepository patientEntityRepository;

  public PatientService(PatientEntityRepository patientEntityRepository) {
    this.patientEntityRepository = patientEntityRepository;
  }

  public Set<PatientDto> fetchPatients() {
    List<PatientEntity> patientEntities = patientEntityRepository.findAll();
    return patientEntities.stream().map(patientEntity -> PatientDto.builder()
            .firstName(patientEntity.getFirstName())
            .lastName(patientEntity.getLastName())
            .build())
        .collect(Collectors.toSet());
  }
}
