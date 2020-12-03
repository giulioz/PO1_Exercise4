package it.unive.dais.po1.exercise4.services;

import it.unive.dais.po1.exercise4.game.boards.Board;
import it.unive.dais.po1.exercise4.game.GameState;
import it.unive.dais.po1.exercise4.game.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {
  static int DEFAULT_DIMENSION = 8;

  private final Map<String, GameState> games = new HashMap<>();

  @Autowired
  private DynamicPlayerLoader playerLoader;
  @Autowired
  private DynamicBoardLoader boardLoader;

  public GameState getGame(String gameId) throws NoSuchGameException {
    if (!games.containsKey(gameId)){
      throw new NoSuchGameException(gameId);
    }
    return games.get(gameId);
  }

  public String createGame(String playerAType, String playerBType, String boardType) throws IllegalPlayerException {
    Player playerA = playerLoader.getPlayer(playerAType);
    Player playerB = playerLoader.getPlayer(playerBType);
    Board board = boardLoader.getBoard(boardType);
    return createGame(playerA, playerB, board);
  }

  public String createGame(Player playerA, Player playerB, Board board) {
    GameState gameState = new GameState(board, new Player[]{playerA, playerB});
    String gameId = UUID.randomUUID().toString();
    games.put(gameId, gameState);
    return gameId;
  }
}
