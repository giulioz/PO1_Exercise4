package it.unive.dais.po1.exercise4.services;

public class IllegalPlayerException extends Exception {
  private final String name;

  public IllegalPlayerException(String name) {
    super("Invalid player name: " + name);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
