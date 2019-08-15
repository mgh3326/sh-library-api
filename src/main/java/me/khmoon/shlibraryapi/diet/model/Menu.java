package me.khmoon.shlibraryapi.diet.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "tableMenus")

public class Menu {
  @Id
  @GeneratedValue
  private Long Id;
  @OneToMany(mappedBy = "menu")
  private List<TableMenu> tableMenus = new ArrayList<>();
  private String name;

  @Builder
  public Menu(String name) {
    this.name = name;
  }

  public void addTableMenu(TableMenu tableMenu) {
    tableMenus.add(tableMenu);
    tableMenu.setMenu(this);
  }
}
