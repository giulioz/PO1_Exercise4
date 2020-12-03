import React from "react";
import { Button, Grid, makeStyles, Paper, Typography } from "@material-ui/core";
import { useStore } from "../store";
import { BoardDisplay } from "../components/BoardDisplay";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    padding: theme.spacing(2),
  },
  column: {
    display: "flex",
    flexDirection: "column",
    marginBottom: theme.spacing(2),
  },
  action: {
    marginBottom: theme.spacing(3),
  },
}));

export function Game() {
  const classes = useStyles();

  const {
    playerAClass,
    playerBClass,
    lastGameState: gameState,
    needsInput,
    nextMove,
    doInput,
  } = useStore();

  function handleNext() {
    nextMove();
  }

  if (!gameState) {
    return null;
  }

  const canProceed =
    !gameState.winner && !gameState.currentBoard.full && !needsInput;

  return (
    <>
      <Paper className={classes.paper}>
        <Typography variant="h4" gutterBottom>
          {canProceed && (
            <>Player {gameState.playerMarks[gameState.lastPlayerIndex]} Turn</>
          )}
          {gameState.winner && <>Game End!</>}
        </Typography>

        <BoardDisplay
          currentBoard={gameState.currentBoard}
          onSelect={doInput}
        />

        {canProceed && (
          <Button
            variant="contained"
            color="primary"
            onClick={handleNext}
            className={classes.action}
          >
            NEXT
          </Button>
        )}

        {gameState.winner && (
          <>
            <Typography gutterBottom variant="h4" color="error">
              {gameState.winner} WON!
            </Typography>
            <Button
              variant="contained"
              color="primary"
              onClick={() => window.location.reload()}
              className={classes.action}
            >
              Restart
            </Button>
          </>
        )}

        {needsInput && (
          <Typography gutterBottom variant="h4" color="primary">
            Select a cell
          </Typography>
        )}

        <Grid container alignItems="center" justify="center" spacing={2}>
          <Grid item xs={8} md={4} className={classes.column}>
            <Typography
              variant="h6"
              gutterBottom
              align="center"
              color={gameState.lastPlayerIndex === 0 ? "primary" : "initial"}
            >
              Player {gameState.playerMarks[0]}
            </Typography>
            <Typography gutterBottom align="center">
              {playerAClass}
            </Typography>
          </Grid>
          <Grid item xs={8} md={4} className={classes.column}>
            <Typography
              variant="h6"
              gutterBottom
              align="center"
              color={gameState.lastPlayerIndex === 1 ? "primary" : "initial"}
            >
              Player {gameState.playerMarks[1]}
            </Typography>
            <Typography gutterBottom align="center">
              {playerBClass}
            </Typography>
          </Grid>
        </Grid>
      </Paper>
    </>
  );
}
