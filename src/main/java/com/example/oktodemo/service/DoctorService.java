package com.example.oktodemo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.DoctorDto;
import com.example.oktodemo.model.dto.TimeSlotDto;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.model.entity.TimeSlotEntity;
import com.example.oktodemo.repository.DoctorEntityRepository;

@Service
public class DoctorService {

  private final DoctorEntityRepository doctorEntityRepository;

  public DoctorService(DoctorEntityRepository doctorEntityRepository) {
    this.doctorEntityRepository = doctorEntityRepository;
  }

  public Set<DoctorDto> fetchDoctors() {
    List<DoctorEntity> doctorEntities = doctorEntityRepository.findAll();
    return doctorEntities.stream().map(doctorEntity -> DoctorDto.builder()
            .firstName(doctorEntity.getFirstName())
            .lastName(doctorEntity.getLastName())
            .build())
        .collect(Collectors.toSet());
  }

  public DoctorEntity fetchDoctorByFirstNameAndLastName(String firstName, String lastName) {
    Optional<DoctorEntity> doctorEntity = doctorEntityRepository.findDoctorEntityByFirstNameAndLastName(firstName, lastName);
    if (doctorEntity.isEmpty()) {
      throw new RuntimeException("There is no doctor with the provided name");
    }
    return doctorEntity.get();
  }

  private Set<TimeSlotDto> transformTimeSlotEntitiesToDtos(Set<TimeSlotEntity> timeSlotEntities) {
    return timeSlotEntities.stream().map(timeSlotEntity -> TimeSlotDto.builder()
            .from(timeSlotEntity.getFrom().toLocalTime())
            .to(timeSlotEntity.getTo().toLocalTime())
            .build())
        .collect(Collectors.toSet());
  }
}
