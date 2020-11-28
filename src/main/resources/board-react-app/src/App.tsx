import React, { Suspense } from "react";
import { Container, CssBaseline } from "@material-ui/core";

import { useStore } from "./store";
import { PlayerSelect } from "./containers/PlayerSelect";
import { Game } from "./containers/Game";

export function App() {
  const currentPage = useStore((s) => s.currentPage);
  return (
    <>
      <CssBaseline />
      <Container component="main" maxWidth="md">
        <Suspense fallback={null}>
          {currentPage === "SELECT_PLAYER" && <PlayerSelect />}
          {currentPage === "GAME" && <Game />}
        </Suspense>
      </Container>
    </>
  );
}
