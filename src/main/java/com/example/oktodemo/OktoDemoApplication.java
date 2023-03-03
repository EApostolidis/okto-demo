package com.example.oktodemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.oktodemo.model.AppointmentEntity;
import com.example.oktodemo.model.DoctorEntity;
import com.example.oktodemo.model.PatientEntity;
import com.example.oktodemo.model.TimeSlotEntity;
import com.example.oktodemo.model.WorkingDayEntity;
import com.example.oktodemo.repository.AppointmentEntityRepository;
import com.example.oktodemo.repository.DoctorEntityRepository;
import com.example.oktodemo.repository.PatientEntityRepository;
import com.example.oktodemo.repository.TimeSlotEntityRepository;
import com.example.oktodemo.repository.WorkingDayEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class OktoDemoApplication implements CommandLineRunner {

  @Autowired
  TimeSlotEntityRepository timeSlotEntityRepository;

  @Autowired
  WorkingDayEntityRepository workingDayEntityRepository;

  @Autowired
  DoctorEntityRepository doctorEntityRepository;

  @Autowired
  AppointmentEntityRepository appointmentEntityRepository;

  @Autowired
  PatientEntityRepository patientEntityRepository;

  public static void main(String[] args) {
    SpringApplication.run(OktoDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    log.info("run process");
    TimeSlotEntity timeSlotEntity = new TimeSlotEntity();
    timeSlotEntity.setFrom(LocalDateTime.now().minusHours(1));
    timeSlotEntity.setTo(LocalDateTime.now());

    TimeSlotEntity timeSlotEntity2 = new TimeSlotEntity();
    timeSlotEntity2.setFrom(LocalDateTime.now().minusHours(1));
    timeSlotEntity2.setTo(LocalDateTime.now());


    WorkingDayEntity workingDayEntity = new WorkingDayEntity();
    workingDayEntity.setDate(LocalDate.now());
    Set<TimeSlotEntity> timeSlotEntities = new HashSet<>();
    timeSlotEntities.add(timeSlotEntity);
    timeSlotEntities.add(timeSlotEntity2);
    workingDayEntity.setTimeSlotEntityList(timeSlotEntities);
    timeSlotEntity.setWorkingDayEntity(workingDayEntity);
    timeSlotEntity2.setWorkingDayEntity(workingDayEntity);

    DoctorEntity doctorEntity = new DoctorEntity();
    doctorEntity.setFirstName("Vaggelis");
    doctorEntity.setLastName("Zivago");
    doctorEntity.getWorkingDayEntities().add(workingDayEntity);
    workingDayEntity.setDoctorEntity(doctorEntity);

    PatientEntity patientEntity = new PatientEntity();
    patientEntity.setFirstName("Vaggelis");
    patientEntity.setLastName("Apostolidis");

    AppointmentEntity appointmentEntity = new AppointmentEntity();
    appointmentEntity.setDate(LocalDate.now());
    appointmentEntity.setFrom(LocalDateTime.now().minusHours(1));
    appointmentEntity.setTo(LocalDateTime.now());
    appointmentEntity.setDoctorEntity(doctorEntity);
    appointmentEntity.setPatientEntity(patientEntity);


    patientEntity.getAppointmentEntities().add(appointmentEntity);
    doctorEntity.getAppointmentEntities().add(appointmentEntity);
    patientEntityRepository.save(patientEntity);
    doctorEntityRepository.save(doctorEntity);
    appointmentEntityRepository.save(appointmentEntity);
    workingDayEntityRepository.save(workingDayEntity);
    timeSlotEntityRepository.save(timeSlotEntity);
    timeSlotEntityRepository.save(timeSlotEntity2);


    List<WorkingDayEntity> result = workingDayEntityRepository.findAll();
    List<DoctorEntity> resultDoctors = doctorEntityRepository.findAll();
    List<AppointmentEntity> resultAppointments = appointmentEntityRepository.findAll();
    List<PatientEntity> patientEntities = patientEntityRepository.findAll();
    log.info("{end of process {}", result.get(0).getTimeSlotEntityList());

  }

}
