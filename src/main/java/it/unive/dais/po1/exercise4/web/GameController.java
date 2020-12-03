package it.unive.dais.po1.exercise4.web;

import it.unive.dais.po1.exercise4.game.GameException;
import it.unive.dais.po1.exercise4.game.GameState;
import it.unive.dais.po1.exercise4.game.InputRequiredException;
import it.unive.dais.po1.exercise4.services.GameService;
import it.unive.dais.po1.exercise4.services.IllegalPlayerException;
import it.unive.dais.po1.exercise4.services.InputPosition;
import it.unive.dais.po1.exercise4.services.NoSuchGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
  @Autowired
  private GameService gameService;

  @RequestMapping(value = "/game", method = RequestMethod.POST)
  public String postNewGame(@RequestBody GameCreateRequestBody body)
          throws IllegalPlayerException {
    return gameService.createGame(
            body.getPlayerAType(),
            body.getPlayerBType(),
            body.getBoardType()
    );
  }

  @RequestMapping(value = "/gameState/{id}")
  public GameState getGameState(@PathVariable("id") String id) throws NoSuchGameException {
    return gameService.getGame(id);
  }

  @RequestMapping(value = "/gameState/{id}", method = RequestMethod.POST)
  public GameState advanceGame(@PathVariable("id") String id)
          throws GameException, InputRequiredException, NoSuchGameException {
    GameState gameState = gameService.getGame(id);
    gameState.play();
    return gameState;
  }

  @RequestMapping(value = "/input/{id}", method = RequestMethod.POST)
  public GameState giveInputGame(
          @PathVariable("id") String id,
          @RequestBody InputPosition position)
          throws NoSuchGameException, GameException, InputRequiredException {
    GameState gameState = gameService.getGame(id);
    gameState.giveInput(position.getX(), position.getY());
    gameState.play();
    return gameState;
  }

  @ExceptionHandler({NoSuchGameException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public String handleNoSuchGameException(NoSuchGameException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler({GameException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleGameException(GameException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler({InputRequiredException.class})
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public String handleInputRequiredException(InputRequiredException ex) {
    return "Needs Input";
  }
}
