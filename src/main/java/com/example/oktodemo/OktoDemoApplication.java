package com.example.oktodemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.oktodemo.model.entity.AppointmentEntity;
import com.example.oktodemo.model.entity.DoctorEntity;
import com.example.oktodemo.model.entity.PatientEntity;
import com.example.oktodemo.model.entity.WorkingDayEntity;
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

    List<WorkingDayEntity> result = workingDayEntityRepository.findAll();
    List<DoctorEntity> resultDoctors = doctorEntityRepository.findAll();
    List<AppointmentEntity> resultAppointments = appointmentEntityRepository.findAll();
    List<PatientEntity> patientEntities = patientEntityRepository.findAll();
    log.info("{end of process {}", result.get(0).getTimeSlotEntityList());

  }

}
