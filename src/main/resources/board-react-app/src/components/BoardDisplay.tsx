import React from "react";
import { makeStyles } from "@material-ui/core";
import { Board } from "../api";

const useStyles = makeStyles((theme) => ({
  board: {
    display: "flex",
    margin: theme.spacing(3),
  },
  row: {},
  cell: {
    border: `1px solid ${theme.palette.text.disabled}`,
    width: theme.spacing(4),
    height: theme.spacing(4),
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    fontWeight: "bold",
  },
}));

export function BoardDisplay({
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
