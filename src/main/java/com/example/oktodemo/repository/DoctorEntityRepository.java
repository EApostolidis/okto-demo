package com.example.oktodemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.entity.DoctorEntity;

@Repository
public interface DoctorEntityRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity> findDoctorEntityByFirstNameAndLastName(String firstName, String lastName);
}
