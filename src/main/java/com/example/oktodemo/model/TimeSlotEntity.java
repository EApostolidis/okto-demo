package com.example.oktodemo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="time_slot")
public class TimeSlotEntity {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDateTime from;

  private LocalDateTime to;

  @ManyToOne(fetch = FetchType.EAGER)
  private WorkingDayEntity workingDayEntity;
}
