package me.khmoon.shlibraryapi.diet.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "dietTableMenus")
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"date", "menuKind"}))
public class DietTable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  LocalDate date;
  @OneToMany(mappedBy = "dietTable")
  private List<DietTableMenu> dietTableMenus = new ArrayList<>();
  @Enumerated(EnumType.STRING)
  private MenuKind menuKind;

  @Builder
  public DietTable(LocalDate date, MenuKind menuKind) {
    this.menuKind = menuKind;
    this.date = date;
  }

  public void addTableMenu(DietTableMenu dietTableMenu) {
    dietTableMenus.add(dietTableMenu);
    dietTableMenu.setDietTable(this);
  }
}
