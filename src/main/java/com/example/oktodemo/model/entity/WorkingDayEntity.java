package com.example.oktodemo.model.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="working_day")
public class WorkingDayEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;

  @OneToMany(mappedBy = "workingDayEntity", fetch = FetchType.EAGER)
  private Set<TimeSlotEntity> timeSlotEntityList = new HashSet<>();

  @ManyToOne(fetch = FetchType.EAGER)
  private DoctorEntity doctorEntity;
}
