package me.khmoon.shlibraryapi.diet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DietTableRes {
  private Long id;
  LocalDate date;
  private MenuKind menuKind;
}
