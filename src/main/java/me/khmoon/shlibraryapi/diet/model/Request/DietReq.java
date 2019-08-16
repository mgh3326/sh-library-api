package me.khmoon.shlibraryapi.diet.model.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DietReq {
  private LocalDate date;
  private String menuKind;

  private List<MenuReq> menus = new ArrayList<>();

  @Builder
  public DietReq(LocalDate date, String menuKind) {
    this.date = date;
    this.menuKind = menuKind;
  }
}

