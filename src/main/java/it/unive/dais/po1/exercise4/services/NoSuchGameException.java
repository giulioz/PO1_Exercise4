package it.unive.dais.po1.exercise4.services;

public class NoSuchGameException extends Exception {
  private final String id;

  public NoSuchGameException(String id) {
    super("No such game: " + id);
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
