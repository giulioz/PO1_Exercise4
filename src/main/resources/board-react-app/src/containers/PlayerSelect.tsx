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
  column: {
    display: "flex",
    flexDirection: "column",
    marginBottom: theme.spacing(2),
  },
}));

export function PlayerSelect() {
  const classes = useStyles();

  const {
    playerClasses,
    playerAClass,
    setPlayerBClass,
    playerBClass,
    setPlayerAClass,
    startGame,
    requirePlayerClasses,
  } = useStore();

  useEffect(() => void requirePlayerClasses(), []);

  function handlePlay() {
    if (playerAClass && playerBClass) {
      startGame();
    }
  }

  if (!playerClasses) {
    return null;
  }

  return (
    <Paper className={classes.paper}>
      <Typography variant="h2" gutterBottom>
        Gomoku
      </Typography>
      <Typography variant="h4" gutterBottom>
        Select Players
      </Typography>
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
