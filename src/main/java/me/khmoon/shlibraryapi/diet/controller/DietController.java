package me.khmoon.shlibraryapi.diet.controller;

import me.khmoon.shlibraryapi.diet.model.*;
import me.khmoon.shlibraryapi.diet.model.Request.DietReq;
import me.khmoon.shlibraryapi.diet.model.Request.MenuReq;
import me.khmoon.shlibraryapi.diet.model.Response.DietTableRes;
import me.khmoon.shlibraryapi.diet.model.Respository.DietTableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/diet")
public class DietController {

  @Autowired
  ModelMapper modelMapper;
  @Autowired
  DietTableRepository dietTableRepository;
  @Autowired
  EntityManager entityManager;

  @PostMapping
  @Transactional
  public ResponseEntity createDiet(@RequestBody DietReq dietReq, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(errors);
    }
    DietTable dietTable = modelMapper.map(dietReq, DietTable.class);
    entityManager.persist(dietTable);
    for (MenuReq menu : dietReq.getMenus()) {
      Menu newMenu = modelMapper.map(menu, Menu.class);
      DietTableMenu dietTableMenu = new DietTableMenu();

      newMenu.addTableMenu(dietTableMenu);
      dietTable.addTableMenu(dietTableMenu);
      entityManager.persist(newMenu);
      entityManager.persist(dietTableMenu);

    }
    DietTable newDietTable = this.dietTableRepository.save(dietTable);
    entityManager.flush();
    entityManager.clear();
    DietTableRes dietTableRes = modelMapper.map(newDietTable, DietTableRes.class);
    List<MenuReq> menus = dietTableRes.getMenus();
    for (DietTableMenu dietTableMenu : newDietTable.getDietTableMenus()) {
      MenuReq menuReq = modelMapper.map(dietTableMenu.getMenu(), MenuReq.class);
      menus.add(menuReq);
    }
    dietTableRes.setMenus(menus);
    URI createUri = ControllerLinkBuilder.linkTo(DietController.class).slash(dietTableRes.getId()).toUri();
    return ResponseEntity.created(createUri).body(dietTableRes);
  }

  @GetMapping
  public String Test() {
    return "Test";
  }
}
