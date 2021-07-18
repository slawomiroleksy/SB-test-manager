package com.example.testmanager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "test")
public class TestEntry {
  @Id
  @GeneratedValue
  private Long id;
  @NotBlank
  private String name;
  @NotNull
  private Status status;
}