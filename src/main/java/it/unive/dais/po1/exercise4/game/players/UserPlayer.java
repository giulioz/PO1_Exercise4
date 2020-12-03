package it.unive.dais.po1.exercise4.game.players;

import it.unive.dais.po1.exercise4.game.*;
import it.unive.dais.po1.exercise4.game.boards.Board;
import org.springframework.stereotype.Component;

@Component("User Player")
public class UserPlayer extends Player {
  private int x = -1;
  private int y = -1;

  @Override
  public void play(Board board, Mark mark) throws GameException, InputRequiredException {
    if (x == -1 || y == -1) {
      throw new InputRequiredException();
    }

    if (board.isFull() || board.winner() != null)
      throw new GameException("Full board");
    if (board.winner() != null)
      throw new GameException("There is already a winner (mark " + board.winner() + ")");

    if (board.getMark(x, y) == null) {
      board.putMark(mark, x, y);
      this.x = -1;
      this.y = -1;
    } else {
      this.x = -1;
      this.y = -1;
      throw new GameException("Position already occupied");
    }
  }

  @Override
  public void giveInput(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
