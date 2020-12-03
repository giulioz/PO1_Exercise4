package it.unive.dais.po1.exercise4.game.boards;

import it.unive.dais.po1.exercise4.game.GameException;
import it.unive.dais.po1.exercise4.game.Mark;

/**
 * This class represents a generic board
 *
 * @since 1.0
 */
abstract public class Board {
  private Mark[][] board;

  /**
   * Creates a square board
   * @param dimension the dimension of the square board
   */
  public Board(int dimension) {
    board = new Mark[dimension][dimension];
  }


  /**
   * Puts a mark in a given cell
   *
   * @param c the mark to put in the board
   * @param x the x coordinate of the cell to be filled
   * @param y the y coordinate of the cell to be filled
   * @return true if the mark is not null, the cell is empty,
   * the game is not ended (e.g., no winner yet) and
   * it fills it, false otherwise
   */
  public boolean putMark(Mark c, int x, int y) throws GameException {
    Mark previous = this.getMark(x, y);
    if(previous!=null || this.winner()!=null || c==null)
      return false;
    board[x][y]=c;
    return true;
  }

  /**
   * Returns the mark of a cell, null if empty
   * @param x the x coordinate of the cell
   * @param y the y coordinate of the cell
   * @return the mark in the given cell, null if the cell is empty
   */
  public Mark getMark(int x, int y) {
    return board[x][y];
  }

  /**
   * Returns the dimension of the square board
   * @return the dimension of the square board
   */
  public int getDimension() {
    return board.length;
  }

  /**
   * Returns true if the board is full
   *
   * @return true iff the board is full
   */
  public boolean isFull() {
    for(int x = 0; x < board.length; x++)
      for(int y = 0; y < board.length; y++)
        if(this.getMark(x, y)==null)
          return false;
    return true;
  }



  public Mark[][] getData() {
    return board;
  }

  @Override
  public String toString() {
    String result ="";
    for(int x = 0; x < this.getDimension(); x++) {
      for (int y = 0; y < this.getDimension(); y++) {
        Mark s = this.getMark(x, y);
        result+=(s== null ? " e " : (s== Mark.getCircle()? " O " : " X " ));
      }
      result+="\n";
    }
    return result;
  }


  /**
   *
   * Check if the last move (where the current instance represents the last status of the
   * board, and parameter previous represents the previous status of the board) was legal,
   * that is, a mark (of the given type) was added to one cell of the board that was
   * previously empty
   *
   * @param previous the status of the board before the last move
   * @param m the mark that should have been added to the board
   * @return true if and only if the last move was valid
   * @throws GameException if the current and previous boards are not compatible
   */
  abstract protected boolean isValidMove(Board previous, Mark m) throws GameException;


  /**
   * Returns the winner of the game
   *
   * @return the mark of the winner of the game, or null if there is not yet a winner
   */
  abstract public Mark winner();

  abstract public Board clone();

}
