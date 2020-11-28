package it.unive.dais.po1.exercise4.game;

/**
 * This class represents an error that occurs during the playing of a game
 */
public class GameException extends Exception {

  public GameException(String message) {
    super(message);
  }

  public GameException(String message, Throwable cause) {
    super(message, cause);
  }
}
