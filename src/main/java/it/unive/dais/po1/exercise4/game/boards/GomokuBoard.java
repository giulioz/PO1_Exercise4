package it.unive.dais.po1.exercise4.game.boards;

import it.unive.dais.po1.exercise4.game.GameException;
import it.unive.dais.po1.exercise4.game.Mark;
import org.springframework.stereotype.Component;

@Component("Gomoku Board")
public class GomokuBoard extends Board {

  public GomokuBoard() {
    super(15);
  }


  /**
   * Returns the winner of the game
   *
   * @return the mark of the winner of the game, or null if there is not yet a winner
   */
  public Mark winner() {
    for (int x = 0; x < this.getDimension(); x++)
      for (int y = 0; y < this.getDimension(); y++) {
        Mark winner = this.winnerAt(x, y);
        if (winner != null)
          return winner;
      }
    return null;
  }

  @Override
  public Board clone() {
    GomokuBoard board = new GomokuBoard();
    for (int i = 0; i < this.getDimension(); i++)
      for (int j = 0; j < this.getDimension(); j++)
        try {
          board.putMark(this.getMark(i, j), i, j);
        } catch (GameException e) {
          throw new RuntimeException("Impossible to clone the board");
        }

    return board;
  }


  protected boolean isValidMove(Board previous, Mark diff) throws GameException {
    boolean first = true;
    if (this.getDimension() != previous.getDimension())
      throw new GameException("Cannot compare two boards of different dimensions");
    for (int x = 0; x < this.getDimension(); x++)
      for (int y = 0; y < this.getDimension(); y++)
        if (this.getMark(x, y) != previous.getMark(x, y))
          if (this.getMark(x, y) == diff) {
            if (first)
              first = false;
            else return false;
          }
    return !first;
  }

  private Mark winnerAt(int x, int y) {
    Mark actual = this.getMark(x, y);
    if (actual == null) return null;
    if (x + 5 <= this.getDimension()) {
      if (actual == this.getMark(x + 1, y) &&
              actual == this.getMark(x + 2, y) &&
              actual == this.getMark(x + 3, y) &&
              actual == this.getMark(x + 4, y))
        return actual;
    }
    if (y + 5 <= this.getDimension()) {
      if (actual == this.getMark(x, y + 1) &&
              actual == this.getMark(x, y + 2) &&
              actual == this.getMark(x, y + 3) &&
              actual == this.getMark(x, y + 4))
        return actual;
    }
    if (x + 5 <= this.getDimension() && y + 5 <= this.getDimension()) {
      if (actual == this.getMark(x + 1, y + 1) &&
              actual == this.getMark(x + 2, y + 2) &&
              actual == this.getMark(x + 3, y + 3) &&
              actual == this.getMark(x + 4, y + 4))
        return actual;
    }
    if (x - 4 >= 0 && y + 5 <= this.getDimension()) {
      if (actual == this.getMark(x - 1, y + 1) &&
              actual == this.getMark(x - 2, y + 2) &&
              actual == this.getMark(x - 3, y + 3) &&
              actual == this.getMark(x - 4, y + 4))
        return actual;
    }
    return null;
  }
}
