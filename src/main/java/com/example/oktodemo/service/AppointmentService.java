package com.example.oktodemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.AppointmentDto;
import com.example.oktodemo.model.dto.DoctorDto;
import com.example.oktodemo.model.dto.PatientDto;
import com.example.oktodemo.model.entity.AppointmentEntity;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.model.entity.PatientEntity;
import com.example.oktodemo.model.entity.TimeSlotEntity;
import com.example.oktodemo.model.request.CreateAppointmentRequest;
import com.example.oktodemo.repository.AppointmentEntityRepository;

import static com.example.oktodemo.utils.TimeUtility.checkIfTimeIsInsidePeriod;
import static com.example.oktodemo.utils.TimeUtility.checkTimeValidity;
import static com.example.oktodemo.utils.TimeUtility.createLocalDateTime;

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

  /**
   * Create the appointment between the Doctor and Patient after validating
   * if there are available working hours and not booked appointments
   * @param request {@link  CreateAppointmentRequest}
   * @return {@link AppointmentDto}
   */
  public AppointmentDto createAppointment(CreateAppointmentRequest request) {
    checkTimeValidity(request.getFrom(), request.getTo());
    DoctorEntity doctorEntity = doctorService.fetchDoctorByFirstNameAndLastName(request.getDoctorFirstName(), request.getDoctorLastName());
    PatientEntity patientEntity = patientService.fetchPatientByFirstNameAndLastName(request.getPatientFirstName(), request.getPatientLastName());
    Optional<AppointmentEntity> bookedAppointment = doctorEntity.getAppointmentEntities().stream()
        .filter(appointment -> appointment.getDate().equals(request.getDate()) &&
            (checkIfTimeIsInsidePeriod(createLocalDateTime(request.getDate(), request.getFrom()), appointment.getFrom(),
                appointment.getTo()) ||
                (checkIfTimeIsInsidePeriod(createLocalDateTime(request.getDate(), request.getTo()), appointment.getFrom(),
                    appointment.getTo()))
            )).findFirst();

    if (bookedAppointment.isPresent()) {
      throw new RuntimeException("There is already booked appointment at that day and hour");
    }
    Optional<Optional<TimeSlotEntity>> timeSlotEntity = Optional.of(doctorEntity.getWorkingDayEntities().stream()
        .filter(workingDayEntity -> workingDayEntity.getDate().equals(request.getDate()))
        .findFirst()
        .map(workingDayEntity -> workingDayEntity.getTimeSlotEntityList().stream()
            .filter(slot ->
                checkIfTimeIsInsidePeriod(createLocalDateTime(request.getDate(), request.getFrom()), slot.getFrom(), slot.getTo())
                    && checkIfTimeIsInsidePeriod(createLocalDateTime(request.getDate(), request.getTo()), slot.getFrom(), slot.getTo()))
            .findFirst()
        )).get();

    if (timeSlotEntity.isEmpty()) {
      throw new RuntimeException("There is no available time for appointment");
    }
    AppointmentEntity appointmentEntity = createAppointmentEntity(request, doctorEntity, patientEntity);
    appointmentEntityRepository.save(appointmentEntity);
    return AppointmentDto.builder()
        .doctor(DoctorDto.builder().firstName(appointmentEntity.getDoctorEntity().getFirstName()).lastName(appointmentEntity.getDoctorEntity().getLastName()).build())
        .patient(PatientDto.builder().firstName(appointmentEntity.getPatientEntity().getFirstName()).lastName(appointmentEntity.getPatientEntity().getLastName()).build())
        .from(appointmentEntity.getFrom().toLocalTime())
        .to(appointmentEntity.getTo().toLocalTime())
        .date(appointmentEntity.getDate())
        .build();
  }

  private AppointmentEntity createAppointmentEntity(CreateAppointmentRequest request, DoctorEntity doctorEntity, PatientEntity patientEntity) {
    AppointmentEntity appointmentEntity = new AppointmentEntity();
    appointmentEntity.setDoctorEntity(doctorEntity);
    appointmentEntity.setPatientEntity(patientEntity);
    appointmentEntity.setDate(request.getDate());
    appointmentEntity.setFrom(createLocalDateTime(request.getDate(), request.getFrom()));
    appointmentEntity.setTo(createLocalDateTime(request.getDate(), request.getTo()));
    return appointmentEntity;
  }
}
