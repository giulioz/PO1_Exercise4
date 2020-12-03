package it.unive.dais.po1.exercise4.game.players;

import it.unive.dais.po1.exercise4.game.boards.Board;
import it.unive.dais.po1.exercise4.game.GameException;
import it.unive.dais.po1.exercise4.game.Mark;
import it.unive.dais.po1.exercise4.game.Player;
import org.springframework.stereotype.Component;

@Component("Dumb Player")
public class DumbPlayer extends Player {
  @Override
  public void play(Board board, Mark mark) throws GameException {
    double dimension = board.getDimension();
    if (board.isFull() || board.winner() != null)
      throw new GameException("Full board");
    if (board.winner() != null)
      throw new GameException("There is already a winner (mark " + board.winner() + ")");
    int x = 0;
    int y = 0;
    while (true) {
      if (board.getMark(x, y) == null) {
        board.putMark(mark, x, y);
        return;
      }

      x++;
      if (x == dimension) {
        y++;
        x = 0;
      }
    }
  }
}
