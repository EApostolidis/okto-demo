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

@Service
public class AppointmentService {

  private final AppointmentEntityRepository appointmentEntityRepository;
  private final DoctorService doctorService;

  private final PatientService patientService;

  public AppointmentService(AppointmentEntityRepository appointmentEntityRepository, DoctorService doctorService, PatientService patientService) {
    this.appointmentEntityRepository = appointmentEntityRepository;
    this.doctorService = doctorService;
    this.patientService = patientService;
  }

  public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
    DoctorEntity doctorEntity = doctorService.fetchDoctorByFirstNameAndLastName(appointmentDto.getDoctor().getFirstName(),
        appointmentDto.getDoctor().getLastName());

    PatientEntity patientEntity = patientService.fetchPatientByFirstNameAndLastName(appointmentDto.getPatient().getFirstName(),
        appointmentDto.getPatient().getLastName());

    Optional<AppointmentEntity> bookedAppointment = doctorEntity.getAppointmentEntities().stream()
        .filter(appointment -> appointment.getDate().equals(appointmentDto.getDate()) &&
            (checkIfTimeIsInsidePeriod(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getFrom()), appointment.getFrom(),
                appointment.getTo()) ||
                (checkIfTimeIsInsidePeriod(createLocalDateTime(appointmentDto.getDate(), appointmentDto.getTo()), appointment.getFrom(),
                    appointment.getTo()))
            )).findFirst();

    if (bookedAppointment.isPresent()) {
      throw new RuntimeException("There is already booked appointment at that day and hour");
    }

    Optional<Optional<TimeSlotEntity>> timeSlotEntity = Optional.of(doctorEntity.getWorkingDayEntities().stream()
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

    AppointmentEntity appointmentEntity = createAppointmentEntity(appointmentDto, doctorEntity, patientEntity);

    appointmentEntityRepository.save(appointmentEntity);
    return appointmentDto;
  }

  private AppointmentEntity createAppointmentEntity(AppointmentDto appointmentDto, DoctorEntity doctorEntity, PatientEntity patientEntity) {
    AppointmentEntity appointmentEntity = new AppointmentEntity();
    appointmentEntity.setDoctorEntity(doctorEntity);
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
    return (check.equals(from) || check.isAfter(from)) && (check.isBefore(to) || check.equals(to));
  }
}
