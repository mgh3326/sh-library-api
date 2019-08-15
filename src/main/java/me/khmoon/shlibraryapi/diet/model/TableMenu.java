package me.khmoon.shlibraryapi.diet.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class TableMenu {

  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TABLE_ID")
  @ToString.Exclude
  private Table tables;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MENU_ID")
  @ToString.Exclude
  private Menu menu;
}
