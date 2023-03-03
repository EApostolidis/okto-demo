package com.example.oktodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.TimeSlotEntity;

@Repository
public interface TimeSlotEntityRepository extends JpaRepository<TimeSlotEntity, Long> {

}
