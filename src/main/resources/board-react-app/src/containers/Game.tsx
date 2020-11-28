import React from "react";
import { Button, Grid, makeStyles, Paper, Typography } from "@material-ui/core";
import { useStore } from "../store";
import { Board } from "../api";

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
  board: {
    display: "flex",
    margin: theme.spacing(3),
  },
  row: {},
  cell: {
    border: "1px solid black",
    width: theme.spacing(4),
    height: theme.spacing(4),
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
}));

function BoardDisplay({
  currentBoard,
  onSelect,
}: {
  currentBoard: Board;
  onSelect(x: number, y: number): void;
}) {
  const classes = useStyles();
  return (
    <div className={classes.board}>
      {currentBoard.data.map((r, i) => (
        <div key={i} className={classes.row}>
          {r.map((c, j) => (
            <div
              key={`${i}-${j}`}
              className={classes.cell}
              onClick={() => onSelect(i, j)}
            >
              {c}
            </div>
          ))}
        </div>
      ))}
    </div>
  );
}

export function Game() {
  const classes = useStyles();

  const {
    playerAClass,
    playerBClass,
    gameState,
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
    <Paper className={classes.paper}>
      <Typography variant="h2" gutterBottom>
        Gomoku
      </Typography>
      <Typography variant="h4" gutterBottom>
        Player {gameState.playerMarks[gameState.lastPlayerIndex]} Turn
      </Typography>

      <BoardDisplay currentBoard={gameState.currentBoard} onSelect={doInput} />

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
        <Typography gutterBottom variant="h4" color="error">
          {gameState.winner} WON!
        </Typography>
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
  );
}