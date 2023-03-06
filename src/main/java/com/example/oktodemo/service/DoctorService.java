package com.example.oktodemo.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.TimeSlotDto;
import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.repository.DoctorEntityRepository;

@Service
public class DoctorService {

  private final DoctorEntityRepository doctorEntityRepository;

  public DoctorService(DoctorEntityRepository doctorEntityRepository) {
    this.doctorEntityRepository = doctorEntityRepository;
  }

  /**
   * Fetch doctor's working hours
   * @param firstName doctor's first name
   * @param lastName doctor's last name
   * @return {@link Set<WorkingDayDto>}
   */
  public Set<WorkingDayDto> fetchDoctorWorkingHours(String firstName, String lastName) {
      return fetchDoctorByFirstNameAndLastName(firstName, lastName).getWorkingDayEntities().stream()
          .map(entity -> WorkingDayDto.builder()
              .date(entity.getDate())
              .timeSlots(entity.getTimeSlotEntityList().stream().map(timeSlotEntity -> TimeSlotDto.builder()
                      .from(timeSlotEntity.getFrom().toLocalTime())
                      .to(timeSlotEntity.getTo().toLocalTime())
                      .build())
                  .collect(Collectors.toSet()))
              .build())
          .collect(Collectors.toSet());
  }

  /**
   * Returns doctor if there is in the database
   * @param firstName doctor's first name
   * @param lastName doctor's last name
   * @return {@link DoctorEntity}
   */
  public DoctorEntity fetchDoctorByFirstNameAndLastName(String firstName, String lastName) {
    Optional<DoctorEntity> doctorEntity = doctorEntityRepository.findDoctorEntityByFirstNameAndLastName(firstName, lastName);
    if (doctorEntity.isEmpty()) {
      throw new RuntimeException("There is no doctor with the provided name");
    }
    return doctorEntity.get();
  }
}
