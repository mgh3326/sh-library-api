package me.khmoon.shlibraryapi;

import me.khmoon.shlibraryapi.diet.model.*;
import me.khmoon.shlibraryapi.diet.model.Respository.DietTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {
  @PersistenceContext
  EntityManager entityManager;
  @Autowired
  DietTableRepository dietTableRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    DietTable dietTable = DietTable.builder().date(LocalDate.now())
            .menuKind(MenuKind.LUNCH)
            .build();
    for (int i = 0; i < 0; i++) {
      DietTableMenu dietTableMenu1 = new DietTableMenu();
      dietTable.addTableMenu(dietTableMenu1);
      Menu menu = Menu.builder().name("백반" + i)
              .build();
      menu.addTableMenu(dietTableMenu1);
      entityManager.persist(menu);
      entityManager.persist(dietTableMenu1);
    }
    entityManager.persist(dietTable);

    entityManager.flush();
    entityManager.clear();
    List<DietTable> all = dietTableRepository.findAll();
    for (DietTable dietTable1 : all) {
      System.out.println("table1 = " + dietTable1);
      List<DietTableMenu> dietTableMenus = dietTable1.getDietTableMenus();
      for (DietTableMenu dietTableMenu : dietTableMenus) {
        String name = dietTableMenu.getMenu().getName();
        System.out.println("name = " + name);
      }
    }
  }

}

