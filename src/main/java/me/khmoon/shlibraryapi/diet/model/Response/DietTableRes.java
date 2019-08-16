package me.khmoon.shlibraryapi.diet.model.Response;

import lombok.Getter;
import lombok.Setter;
import me.khmoon.shlibraryapi.diet.model.Menu;
import me.khmoon.shlibraryapi.diet.model.MenuKind;
import me.khmoon.shlibraryapi.diet.model.Request.MenuReq;

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
  private LocalDate date;
  private MenuKind menuKind;
  private List<MenuReq> menus = new ArrayList<>();

}
