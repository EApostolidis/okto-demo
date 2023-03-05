package com.example.oktodemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.entity.PatientEntity;

@Repository
public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {

  Optional<PatientEntity> findPatientEntitiesByFirstNameAndLastName(String firstName, String lastName);

}
