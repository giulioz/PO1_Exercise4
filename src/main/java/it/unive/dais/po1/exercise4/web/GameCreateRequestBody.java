package it.unive.dais.po1.exercise4.web;

public class GameCreateRequestBody {
  private final String playerAType;
  private final String playerBType;

  public GameCreateRequestBody(String playerAType, String playerBType) {
    this.playerAType = playerAType;
    this.playerBType = playerBType;
  }

  public String getPlayerAType() {
    return playerAType;
  }

  public String getPlayerBType() {
    return playerBType;
  }
}
