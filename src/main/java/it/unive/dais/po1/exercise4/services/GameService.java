package it.unive.dais.po1.exercise4.services;

import it.unive.dais.po1.exercise4.game.Board;
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

  public GameState getGame(String gameId) throws NoSuchGameException {
    if (!games.containsKey(gameId)){
      throw new NoSuchGameException(gameId);
    }
    return games.get(gameId);
  }

  public String createGame(String playerAType, String playerBType) throws IllegalPlayerException {
    Player playerA = playerLoader.getPlayer(playerAType);
    Player playerB = playerLoader.getPlayer(playerBType);
    return createGame(playerA, playerB);
  }

  public String createGame(Player playerA, Player playerB) {
    Board board = new Board(DEFAULT_DIMENSION);
    GameState gameState = new GameState(board, new Player[]{playerA, playerB});
    String gameId = UUID.randomUUID().toString();
    games.put(gameId, gameState);
    return gameId;
  }
}
