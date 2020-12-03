import React, { useEffect } from "react";
import {
  Button,
  Grid,
  makeStyles,
  MenuItem,
  Paper,
  Select,
  Typography,
} from "@material-ui/core";
import { useStore } from "../store";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    padding: theme.spacing(2),
  },
  settings: {
    marginTop: theme.spacing(2),
    marginBottom: theme.spacing(5),
    alignSelf: "stretch",
  },
  column: {
    display: "flex",
    flexDirection: "column",
  },
}));

export function PlayerSelect() {
  const classes = useStyles();

  const {
    playerClasses,
    requirePlayerClasses,
    boardClasses,
    requireBoardClasses,
    playerAClass,
    setPlayerBClass,
    playerBClass,
    setPlayerAClass,
    boardType,
    setBoardType,
    startGame,
  } = useStore();

  useEffect(() => void requirePlayerClasses(), []);
  useEffect(() => void requireBoardClasses(), []);

  function handlePlay() {
    if (playerAClass && playerBClass && boardType) {
      startGame();
    }
  }

  if (!playerClasses || !boardClasses) {
    return null;
  }

  return (
    <Paper className={classes.paper}>
      <Typography variant="h2" gutterBottom>
        Board Game
      </Typography>
      <Typography variant="h4" gutterBottom>
        Settings
      </Typography>

      <div className={classes.settings}>
        <Grid container alignItems="center" justify="center" spacing={2}>
          <Grid item xs={8} md={4} className={classes.column}>
            <Typography variant="h6" gutterBottom align="center">
              Player A
            </Typography>
            <Select
              value={playerAClass}
              onChange={(e) => setPlayerAClass(e.target.value as string)}
              variant="outlined"
            >
              {playerClasses.map((player) => (
                <MenuItem key={player} value={player}>
                  {player}
                </MenuItem>
              ))}
            </Select>
          </Grid>
          <Grid item xs={8} md={4} className={classes.column}>
            <Typography variant="h6" gutterBottom align="center">
              Player B
            </Typography>
            <Select
              value={playerBClass}
              onChange={(e) => setPlayerBClass(e.target.value as string)}
              variant="outlined"
            >
              {playerClasses.map((player) => (
                <MenuItem key={player} value={player}>
                  {player}
                </MenuItem>
              ))}
            </Select>
          </Grid>
        </Grid>
        <Grid container alignItems="center" justify="center" spacing={2}>
          <Grid item xs={8} md={4} className={classes.column}>
            <Typography variant="h6" gutterBottom align="center">
              Board
            </Typography>
            <Select
              value={boardType}
              onChange={(e) => setBoardType(e.target.value as string)}
              variant="outlined"
            >
              {boardClasses.map((board) => (
                <MenuItem key={board} value={board}>
                  {board}
                </MenuItem>
              ))}
            </Select>
          </Grid>
        </Grid>
      </div>

      <Button
        variant="contained"
        color="primary"
        onClick={handlePlay}
        disabled={!playerAClass || !playerBClass}
      >
        PLAY
      </Button>
    </Paper>
  );
}
