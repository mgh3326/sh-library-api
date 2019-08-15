package me.khmoon.shlibraryapi.diet.model;

import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DietTableMenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DIET_TABLE_ID")
  @ToString.Exclude
  private DietTable dietTable;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "MENU_ID")
  @ToString.Exclude
  private Menu menu;
}
