package it.unive.dais.po1.exercise4.game;

import it.unive.dais.po1.exercise4.game.boards.Board;

public class GameState {
  private final Board currentBoard;
  private final Player[] players;
  private final Mark[] playerMarks = {Mark.getCircle(), Mark.getCross()};
  private int playerIndex;

  public GameState(Board currentBoard, Player[] players) {
    this.currentBoard = currentBoard;
    this.players = players;
    this.playerIndex = 0;
  }

  public Board getCurrentBoard() {
    return currentBoard;
  }

  public int getLastPlayerIndex() {
    return playerIndex;
  }

  public Mark getWinner() {
    return currentBoard.winner();
  }

  public Mark[] getPlayerMarks() {
    return playerMarks;
  }

  private void switchPlayer() {
    playerIndex++;
    if (playerIndex >= players.length) {
      playerIndex = 0;
    }
  }

  public void play() throws GameException, InputRequiredException {
    Player p = players[playerIndex];
    Mark m = playerMarks[playerIndex];
    p.play(currentBoard, m);

    switchPlayer();
  }

  public void giveInput(int x, int y) {
    Player p = players[playerIndex];
    p.giveInput(x, y);
  }
}
