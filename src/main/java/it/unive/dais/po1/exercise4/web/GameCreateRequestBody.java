package it.unive.dais.po1.exercise4.web;

public class GameCreateRequestBody {
  private final String playerAType;
  private final String playerBType;
  private final String boardType;

  public GameCreateRequestBody(String playerAType, String playerBType, String boardType) {
    this.playerAType = playerAType;
    this.playerBType = playerBType;
    this.boardType = boardType;
  }

  public String getPlayerAType() {
    return playerAType;
  }

  public String getPlayerBType() {
    return playerBType;
  }

  public String getBoardType() { return boardType;}
}
