package com.example.oktodemo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.dto.DoctorDto;
import com.example.oktodemo.model.dto.PatientDto;
import com.example.oktodemo.model.entity.AppointmentEntity;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.model.entity.PatientEntity;
import com.example.oktodemo.repository.AppointmentEntityRepository;
import com.example.oktodemo.repository.DoctorEntityRepository;

@Service
public class AppointmentService {

  private final AppointmentEntityRepository appointmentEntityRepository;
  private final DoctorEntityRepository doctorEntityRepository;

  public AppointmentService(AppointmentEntityRepository appointmentEntityRepository, DoctorEntityRepository doctorEntityRepository) {
    this.appointmentEntityRepository = appointmentEntityRepository;
    this.doctorEntityRepository = doctorEntityRepository;
  }

  public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
    Optional<AppointmentEntity> appointment = appointmentEntityRepository
        .findAppointmentEntityByDoctorEntity_FirstNameAndDoctorEntity_LastName(appointmentDto.getDoctor().getFirstName(),
            appointmentDto.getDoctor().getLastName());
    return appointment.map(appointmentEntity -> AppointmentDto.builder().build()).orElseThrow(() -> new RuntimeException("There "));
  }

  public Set<AppointmentDto> fetchAppointments() {
    List<AppointmentEntity> appointmentEntities = appointmentEntityRepository.findAll();
    return appointmentEntities.stream().map(appointmentEntity ->
            AppointmentDto.builder()
                .date(appointmentEntity.getDate())
                .from(appointmentEntity.getFrom().toLocalTime())
                .to(appointmentEntity.getTo().toLocalTime())
                .doctor(transformDoctorEntityToDto(appointmentEntity.getDoctorEntity()))
                .patient(transformPatientEntityToDto(appointmentEntity.getPatientEntity()))
                .build())
        .collect(Collectors.toSet());
  }

  private DoctorDto transformDoctorEntityToDto(DoctorEntity doctorEntity) {
    return DoctorDto.builder()
        .firstName(doctorEntity.getFirstName())
        .lastName(doctorEntity.getLastName())
        .build();
  }

  private PatientDto transformPatientEntityToDto(PatientEntity patientEntity) {
    return PatientDto.builder()
        .firstName(patientEntity.getFirstName())
        .lastName(patientEntity.getLastName())
        .build();
  }
}
