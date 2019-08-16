package me.khmoon.shlibraryapi.diet.controller;

import lombok.extern.slf4j.Slf4j;
import me.khmoon.shlibraryapi.diet.model.*;
import me.khmoon.shlibraryapi.diet.model.Request.DietReq;
import me.khmoon.shlibraryapi.diet.model.Request.MenuReq;
import me.khmoon.shlibraryapi.diet.model.Response.DietTableRes;
import me.khmoon.shlibraryapi.diet.model.Respository.DietTableRepository;
import me.khmoon.shlibraryapi.diet.service.DietService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/diet")
@Slf4j
public class DietController {
  @Autowired
  DietService dietService;


  @PostMapping
  @Transactional
  public ResponseEntity createDiet(@RequestBody DietReq dietReq, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(errors);
    }
    try {
      DietTableRes dietTableRes = dietService.createDietDB(dietReq);
      URI createUri = ControllerLinkBuilder.linkTo(DietController.class).slash(dietTableRes.getId()).toUri();
      return ResponseEntity.created(createUri).body(dietTableRes);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }


  @GetMapping
  public String Test() {
    return "Test";
  }
}
