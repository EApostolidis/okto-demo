package com.example.oktodemo.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patient")
public class PatientEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy = "patientEntity", fetch = FetchType.EAGER)
  private Set<AppointmentEntity> appointmentEntities = new HashSet<>();

}
