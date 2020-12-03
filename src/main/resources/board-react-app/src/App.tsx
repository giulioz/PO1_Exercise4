import React, { Suspense } from "react";
import {
  Container,
  createMuiTheme,
  CssBaseline,
  MuiThemeProvider,
} from "@material-ui/core";

import { useStore } from "./store";
import { PlayerSelect } from "./containers/PlayerSelect";
import { Game } from "./containers/Game";

const theme = createMuiTheme({
  palette: {
    type: "dark",
    primary: { main: "#f44336" },
  },
  typography: {
    fontFamily: `"Inter"`,
  },
});

export function App() {
  const currentPage = useStore((s) => s.currentPage);
  return (
    <MuiThemeProvider theme={theme}>
      <CssBaseline />
      <Container component="main" maxWidth="md">
        <Suspense fallback={null}>
          {currentPage === "SELECT_PLAYER" && <PlayerSelect />}
          {currentPage === "GAME" && <Game />}
        </Suspense>
      </Container>
    </MuiThemeProvider>
  );
}
