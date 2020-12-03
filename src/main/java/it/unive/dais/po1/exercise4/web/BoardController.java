package it.unive.dais.po1.exercise4.web;

import it.unive.dais.po1.exercise4.services.DynamicBoardLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
  @Autowired
  private DynamicBoardLoader boardLoader;

  @RequestMapping(value = "/boardTypes")
  public List<String> getBoardTypes() {
    return boardLoader.getBoardNames();
  }
}
