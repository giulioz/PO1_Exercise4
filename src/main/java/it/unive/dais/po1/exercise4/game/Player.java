package it.unive.dais.po1.exercise4.game;

public abstract class Player {
  abstract public void play(Board board, Mark mark) throws GameException, InputRequiredException;

  public void giveInput(int x, int y) {
    throw new RuntimeException("Not implemented!");
  }
}
