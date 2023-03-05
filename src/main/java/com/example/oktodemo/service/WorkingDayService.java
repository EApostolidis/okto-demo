package com.example.oktodemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.oktodemo.model.dto.WorkingDayDto;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.model.entity.TimeSlotEntity;
import com.example.oktodemo.model.entity.WorkingDayEntity;
import com.example.oktodemo.model.request.CreateOrUpdateDoctorWorkingDayRequest;
import com.example.oktodemo.repository.TimeSlotEntityRepository;
import com.example.oktodemo.repository.WorkingDayEntityRepository;

import static com.example.oktodemo.utils.TimeUtility.createLocalDateTime;

@Service
public class WorkingDayService {

  private final WorkingDayEntityRepository workingDayEntityRepository;

  private final TimeSlotEntityRepository timeSlotEntityRepository;
  private final DoctorService doctorService;


  public WorkingDayService(WorkingDayEntityRepository workingDayEntityRepository, TimeSlotEntityRepository timeSlotEntityRepository,
      DoctorService doctorService) {
    this.workingDayEntityRepository = workingDayEntityRepository;
    this.timeSlotEntityRepository = timeSlotEntityRepository;
    this.doctorService = doctorService;
  }

  public WorkingDayDto updateWorkingDayAndTimeSlots(CreateOrUpdateDoctorWorkingDayRequest request) {
    DoctorEntity doctorEntity = doctorService.fetchDoctorByFirstNameAndLastName(request.getFirstName(), request.getLastName());
    Optional<WorkingDayEntity> workingDayEntity = doctorEntity.getWorkingDayEntities().stream()
        .filter(entity -> entity.getDate().equals(request.getDate()))
        .findFirst();

    WorkingDayEntity entity = workingDayEntity.isPresent() ? createNewTimeSlot(request, workingDayEntity.get()) : createNewWorkingEntity(request, doctorEntity);

    return WorkingDayDto.builder()
        .date(entity.getDate())
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
