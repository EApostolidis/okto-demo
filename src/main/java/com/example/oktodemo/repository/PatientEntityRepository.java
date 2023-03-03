package com.example.oktodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.PatientEntity;

@Repository
public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {

}
