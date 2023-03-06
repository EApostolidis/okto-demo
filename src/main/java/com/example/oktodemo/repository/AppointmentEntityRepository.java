package com.example.oktodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.entity.AppointmentEntity;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {
}
