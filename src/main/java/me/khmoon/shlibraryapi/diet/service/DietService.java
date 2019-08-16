package me.khmoon.shlibraryapi.diet.service;

import me.khmoon.shlibraryapi.diet.model.DietTable;
import me.khmoon.shlibraryapi.diet.model.DietTableMenu;
import me.khmoon.shlibraryapi.diet.model.Menu;
import me.khmoon.shlibraryapi.diet.model.Request.DietReq;
import me.khmoon.shlibraryapi.diet.model.Request.MenuReq;
import me.khmoon.shlibraryapi.diet.model.Response.DietTableRes;
import me.khmoon.shlibraryapi.diet.model.Respository.DietTableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DietService {
  @Autowired
  ModelMapper modelMapper;
  @Autowired
  DietTableRepository dietTableRepository;
  @Autowired
  EntityManager entityManager;

  @Transactional
  public DietTableRes createDietDB(@RequestBody DietReq dietReq) {
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
    DietTableRes dietTableRes = modelMapper.map(newDietTable, DietTableRes.class);
    List<MenuReq> menus = dietTableRes.getMenuResList();
    for (DietTableMenu dietTableMenu : newDietTable.getDietTableMenus()) {
      MenuReq menuReq = modelMapper.map(dietTableMenu.getMenu(), MenuReq.class);
      menus.add(menuReq);
    }
    dietTableRes.setMenuResList(menus);
    return dietTableRes;
  }
}
