package it.unive.dais.po1.exercise4.services;

import it.unive.dais.po1.exercise4.game.boards.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicBoardLoader {
  private final List<Board> boardTypes;

  @Autowired
  public DynamicBoardLoader(List<Board> boardTypes) {
    this.boardTypes = boardTypes;
  }

  public List<Board> getBoardTypes() {
    return boardTypes;
  }

  public List<String> getBoardNames() {
    List<Board> boardTypes = getBoardTypes();
    ArrayList<String> boardNames = new ArrayList<>();
    for (Board type : boardTypes) {
      boardNames.add(type.getClass().getAnnotation(Component.class).value());
    }
    return boardNames;
  }

  public Board getBoard(String name) throws IllegalPlayerException {
    for (Board p : boardTypes) {
      if (p.getClass().getAnnotation(Component.class).value().equals(name)) {
        return p.clone();
      }
    }

    throw new IllegalPlayerException(name);
  }
}
