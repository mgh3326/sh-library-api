package me.khmoon.shlibraryapi.diet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.khmoon.shlibraryapi.diet.model.Request.DietReq;
import me.khmoon.shlibraryapi.diet.model.MenuKind;
import me.khmoon.shlibraryapi.diet.model.Request.MenuReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DietControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void createDiet() throws Exception {

    DietReq dietReq = DietReq.builder()
            .date(LocalDate.of(2019, 8, 12))
            .menuKind(MenuKind.LUNCH.name())
            .build();
    List<MenuReq> menuReqList = dietReq.getMenus();
    MenuReq menuReq = MenuReq.builder().name("밥1").build();
    menuReqList.add(menuReq);
    menuReqList.add(MenuReq.builder().name("밥2").build());
    menuReqList.add(MenuReq.builder().name("밥3").build());
    dietReq.setMenus(menuReqList);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/diet")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaTypes.HAL_JSON)
            .content(objectMapper.writeValueAsString(dietReq))
    ).andDo(print())
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
            .andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION))
            .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.jsonPath("date").value("2019-08-12"))
            .andExpect(MockMvcResultMatchers.jsonPath("menuKind").value(MenuKind.LUNCH.name()))
//            .andExpect(MockMvcResultMatchers.jsonPath("menus").value(true))
    ;

  }
}