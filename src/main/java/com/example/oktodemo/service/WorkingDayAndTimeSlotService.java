package com.example.oktodemo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.TimeSlotDto;
import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.model.entity.TimeSlotEntity;
import com.example.oktodemo.model.entity.WorkingDayEntity;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;
import com.example.oktodemo.repository.TimeSlotEntityRepository;
import com.example.oktodemo.repository.WorkingDayEntityRepository;

import static com.example.oktodemo.utils.TimeUtility.checkTimeValidity;
import static com.example.oktodemo.utils.TimeUtility.createLocalDateTime;

@Service
public class WorkingDayAndTimeSlotService {

  private final WorkingDayEntityRepository workingDayEntityRepository;

  private final TimeSlotEntityRepository timeSlotEntityRepository;
  private final DoctorService doctorService;


  public WorkingDayAndTimeSlotService(WorkingDayEntityRepository workingDayEntityRepository, TimeSlotEntityRepository timeSlotEntityRepository,
      DoctorService doctorService) {
    this.workingDayEntityRepository = workingDayEntityRepository;
    this.timeSlotEntityRepository = timeSlotEntityRepository;
    this.doctorService = doctorService;
  }

  public WorkingDayDto updateWorkingDayAndTimeSlots(CreateOrUpdateDoctorWorkingDayRequest request) {
    checkTimeValidity(request.getFrom(), request.getTo());
    DoctorEntity doctorEntity = doctorService.fetchDoctorByFirstNameAndLastName(request.getDoctorFirstName(), request.getDoctorLastName());
    Optional<WorkingDayEntity> workingDayEntity = doctorEntity.getWorkingDayEntities().stream()
        .filter(entity -> entity.getDate().equals(request.getDate()))
        .findFirst();

    WorkingDayEntity entity = workingDayEntity.map(dayEntity -> createNewTimeSlot(request, dayEntity))
        .orElseGet(() -> createNewWorkingEntity(request, doctorEntity));

    return WorkingDayDto.builder()
        .date(entity.getDate())
        .timeSlots(entity.getTimeSlotEntityList().stream().map(timeSlot -> TimeSlotDto.builder()
                .to(timeSlot.getTo().toLocalTime())
                .from(timeSlot.getFrom().toLocalTime())
            .build())
            .collect(Collectors.toSet()))
        .build();
  }

  private WorkingDayEntity createNewWorkingEntity(CreateOrUpdateDoctorWorkingDayRequest request, DoctorEntity doctorEntity) {
    WorkingDayEntity workingDayEntity = new WorkingDayEntity();
    workingDayEntity.setDoctorEntity(doctorEntity);
    workingDayEntity.setDate(request.getDate());
    workingDayEntityRepository.save(workingDayEntity);
    return createNewTimeSlot(request, workingDayEntity);
  }

  private WorkingDayEntity createNewTimeSlot(CreateOrUpdateDoctorWorkingDayRequest request, WorkingDayEntity workingDayEntity) {
    TimeSlotEntity timeSlotEntity = new TimeSlotEntity();
    timeSlotEntity.setFrom(createLocalDateTime(request.getDate(), request.getFrom()));
    timeSlotEntity.setTo(createLocalDateTime(request.getDate(), request.getTo()));
    timeSlotEntity.setWorkingDayEntity(workingDayEntity);
    timeSlotEntityRepository.save(timeSlotEntity);
    workingDayEntity.getTimeSlotEntityList().add(timeSlotEntity);
    return workingDayEntity;
  }
}
