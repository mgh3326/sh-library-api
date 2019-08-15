package me.khmoon.shlibraryapi;

import me.khmoon.shlibraryapi.diet.model.Menu;
import me.khmoon.shlibraryapi.diet.model.MenuKind;
import me.khmoon.shlibraryapi.diet.model.Table;
import me.khmoon.shlibraryapi.diet.model.TableMenu;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {
  @PersistenceContext
  EntityManager entityManager;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    Table table = Table.builder().date(LocalDate.now())
            .menuKind(MenuKind.LUNCH)
            .build();
    for (int i = 0; i < 10; i++) {
      TableMenu tableMenu1 = new TableMenu();
      table.addTableMenu(tableMenu1);
      Menu menu = Menu.builder().name("백반" + i)
              .build();
      menu.addTableMenu(tableMenu1);
      entityManager.persist(menu);
      entityManager.persist(tableMenu1);
    }
    entityManager.persist(table);


//    List<TableMenu> tableMenus = table.getTableMenus();
//    tableMenus.add(tableMenu);
//    table.setTableMenus(tableMenus);
//    menu.setTableMenus(tableMenus);

  }
}
