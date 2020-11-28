package it.unive.dais.po1.exercise4.services;

import it.unive.dais.po1.exercise4.game.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicPlayerLoader {
  private final List<Player> playerTypes;

  @Autowired
  public DynamicPlayerLoader(List<Player> playerTypes) {
    this.playerTypes = playerTypes;
  }

  public List<Player> getPlayerTypes() {
    return playerTypes;
  }

  public List<String> getPlayerNames() {
    List<Player> playerTypes = getPlayerTypes();
    ArrayList<String> playerNames = new ArrayList<>();
    for (Player type : playerTypes) {
      playerNames.add(type.getClass().getAnnotation(Component.class).value());
    }
    return playerNames;
  }

  public Player getPlayer(String name) throws IllegalPlayerException {
    for (Player p : playerTypes) {
      if (p.getClass().getAnnotation(Component.class).value().equals(name)) {
        return p;
      }
    }

    throw new IllegalPlayerException(name);
  }
}
