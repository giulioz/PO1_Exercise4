package it.unive.dais.po1.exercise4.web;

import it.unive.dais.po1.exercise4.services.DynamicPlayerLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
  @Autowired
  private DynamicPlayerLoader playerLoader;

  @RequestMapping(value = "/playerTypes")
  public List<String> getPlayerTypes() {
    return playerLoader.getPlayerNames();
  }
}
