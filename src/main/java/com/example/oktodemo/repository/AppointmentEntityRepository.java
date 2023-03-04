package com.example.oktodemo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.entity.AppointmentEntity;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {
    Set<AppointmentEntity> findAppointmentEntitiesByDoctorEntity_FirstNameAndDoctorEntity_LastName(String firstName, String lastName);
}
