package it.unive.dais.po1.exercise4.game;

public class Board {
  private final Mark[][] board;

  /**
   * Creates a square board
   *
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
    if (previous != null || this.winner() != null || c == null)
      return false;
    board[x][y] = c;
    return true;
  }

  /**
   * Returns the mark of a cell, null if empty
   *
   * @param x the x coordinate of the cell
   * @param y the y coordinate of the cell
   * @return the mark in the given cell, null if the cell is empty
   */
  public Mark getMark(int x, int y) {
    return board[x][y];
  }

  /**
   * Returns the dimension of the square board
   *
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
    for (int x = 0; x < board.length; x++)
      for (int y = 0; y < board.length; y++)
        if (this.getMark(x, y) == null)
          return false;
    return true;
  }

  @Override
  public String toString() {
    String result = "";
    for (int x = 0; x < this.getDimension(); x++) {
      for (int y = 0; y < this.getDimension(); y++) {
        Mark s = this.getMark(x, y);
        result += (s == null ? " e " : (s == Mark.getCircle() ? " O " : " X "));
      }
      result += "\n";
    }
    return result;
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
    if (x + 5 < this.getDimension()) {
      if (actual == this.getMark(x + 1, y) &&
              actual == this.getMark(x + 2, y) &&
              actual == this.getMark(x + 3, y) &&
              actual == this.getMark(x + 4, y))
        return actual;
    }
    if (y + 5 < this.getDimension()) {
      if (actual == this.getMark(x, y + 1) &&
              actual == this.getMark(x, y + 2) &&
              actual == this.getMark(x, y + 3) &&
              actual == this.getMark(x, y + 4))
        return actual;
    }
    if (x + 5 < this.getDimension() && y + 5 < this.getDimension()) {
      if (actual == this.getMark(x + 1, y + 1) &&
              actual == this.getMark(x + 2, y + 2) &&
              actual == this.getMark(x + 3, y + 3) &&
              actual == this.getMark(x + 4, y + 4))
        return actual;
    }
    if (x - 4 >= 0 && y + 5 < this.getDimension()) {
      if (actual == this.getMark(x - 1, y + 1) &&
              actual == this.getMark(x - 2, y + 2) &&
              actual == this.getMark(x - 3, y + 3) &&
              actual == this.getMark(x - 4, y + 4))
        return actual;
    }
    return null;
  }

  public Mark[][] getData() {
    return board;
  }
}
