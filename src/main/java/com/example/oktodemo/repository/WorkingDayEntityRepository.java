package com.example.oktodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oktodemo.model.WorkingDayEntity;

@Repository
public interface WorkingDayEntityRepository extends JpaRepository<WorkingDayEntity, Long> {

}
