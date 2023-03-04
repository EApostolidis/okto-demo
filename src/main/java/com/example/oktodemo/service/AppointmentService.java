package com.example.oktodemo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.example.oktodemo.model.entity.TimeSlotEntity;
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
    Optional<DoctorEntity> doctorEntity = doctorEntityRepository.findDoctorEntityByFirstNameAndLastName(appointmentDto.getDoctor().getFirstName(),
        appointmentDto.getDoctor().getLastName());
    if (doctorEntity.isEmpty()) {
      throw new RuntimeException("There is no doctor with the provided name");
    }
    Optional<AppointmentEntity> bookedAppointment = doctorEntity.get().getAppointmentEntities().stream()
        .filter(appointment -> appointment.getDate().equals(appointmentDto.getDate()) &&
            (checkIfTimeIsInsidePeriod(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getFrom()), appointment.getFrom(),
                appointment.getTo()) ||
                (checkIfTimeIsInsidePeriod(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getTo()), appointment.getFrom(),
                    appointment.getTo()))
            )).findFirst();

    if (bookedAppointment.isPresent()) {
      throw new RuntimeException("There is already booked appointment at that day and hour");
    }

    Optional<Optional<TimeSlotEntity>> timeSlotEntity = Optional.of(doctorEntity.get().getWorkingDayEntities().stream()
        .filter(workingDayEntity -> workingDayEntity.getDate().equals(appointmentDto.getDate()))
        .findFirst()
        .map(workingDayEntity -> workingDayEntity.getTimeSlotEntityList().stream()
            .filter(slot ->
                checkIfTimeIsInsidePeriod(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getFrom()), slot.getFrom(), slot.getTo())
                    && checkIfTimeIsInsidePeriod(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getTo()), slot.getFrom(), slot.getTo()))
            .findFirst()
        )).get();

    if (timeSlotEntity.isEmpty()) {
      throw new RuntimeException("There is no available time for appointment");
    }

    AppointmentEntity appointmentEntity = createAppointmentEntity(appointmentDto, doctorEntity);

    appointmentEntityRepository.save(appointmentEntity);
    return appointmentDto;
  }

  private AppointmentEntity createAppointmentEntity(AppointmentDto appointmentDto, Optional<DoctorEntity> doctorEntity) {
    AppointmentEntity appointmentEntity = new AppointmentEntity();
    PatientEntity patientEntity = new PatientEntity();
    patientEntity.setFirstName(appointmentDto.getPatient().getFirstName());
    patientEntity.setFirstName(appointmentDto.getPatient().getLastName());
    appointmentEntity.setDoctorEntity(doctorEntity.get());
    appointmentEntity.setPatientEntity(patientEntity);
    appointmentEntity.setDate(appointmentDto.getDate());
    appointmentEntity.setFrom(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getFrom()));
    appointmentEntity.setTo(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getTo()));
    return appointmentEntity;
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

  private LocalDateTime createLocalDateTime(LocalDate date, LocalTime time) {
    return LocalDateTime.of(date, time);
  }

  private boolean checkIfTimeIsInsidePeriod(LocalDateTime check, LocalDateTime from, LocalDateTime to) {
    return check.isAfter(from) && check.isBefore(to);
  }
}
