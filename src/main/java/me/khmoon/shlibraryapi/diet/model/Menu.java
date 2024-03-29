package me.khmoon.shlibraryapi.diet.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "dietTableMenus")
@NoArgsConstructor
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  @OneToMany(mappedBy = "menu")
  private List<DietTableMenu> dietTableMenus = new ArrayList<>();
  private String name;

  @Builder
  public Menu(String name) {
    this.name = name;
  }

  public void addTableMenu(DietTableMenu dietTableMenu) {
    dietTableMenus.add(dietTableMenu);
    dietTableMenu.setMenu(this);
  }
}
