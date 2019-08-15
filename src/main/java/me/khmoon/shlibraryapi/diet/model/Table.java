package me.khmoon.shlibraryapi.diet.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tables")
@Getter
@Setter
@ToString(exclude = "tableMenus")
public class Table {
  @Id
  @GeneratedValue
  private Long id;
  LocalDate date;
  @OneToMany(mappedBy = "tables")
  private List<TableMenu> tableMenus = new ArrayList<>();
  @Enumerated(EnumType.STRING)
  private MenuKind menuKind;

  @Builder
  public Table(LocalDate date, MenuKind menuKind) {
    this.menuKind = menuKind;
    this.date = date;
  }

  public void addTableMenu(TableMenu tableMenu) {
    tableMenus.add(tableMenu);
    tableMenu.setTables(this);
  }
}
