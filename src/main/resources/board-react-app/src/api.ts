export interface Board {
  data: string[][];
  dimension: number;
  full: boolean;
}

export interface GameState {
  currentBoard: Board;
  playerMarks: string[];
  winner?: string | null;
  lastPlayerIndex: number;
}

export const apiBase = "";

export async function getPlayerTypes() {
  const res = await fetch(`${apiBase}/playerTypes`);
  const data: string[] = await res.json();
  return data;
}

export async function getBoardTypes() {
  const res = await fetch(`${apiBase}/boardTypes`);
  const data: string[] = await res.json();
  return data;
}

export async function postNewGame(
  playerAType: string,
  playerBType: string,
  boardType: string,
) {
  const res = await fetch(`${apiBase}/game`, {
    method: "POST",
    body: JSON.stringify({ playerAType, playerBType, boardType }),
    headers: { "Content-Type": "application/json" },
  });
  const data: string = await res.text();
  return data;
}

export async function getGameState(gameId: string) {
  const res = await fetch(`${apiBase}/gameState/${gameId}`);
  const data: GameState = await res.json();
  return data;
}

export async function advanceGame(gameId: String) {
  const res = await fetch(`${apiBase}/gameState/${gameId}`, {
    method: "POST",
  });

  if (res.status === 202) {
    return "NEEDS_INPUT";
  } else {
    const data: GameState = await res.json();
    return data;
  }
}

export async function giveInputGame(gameId: string, x: number, y: number) {
  const res = await fetch(`${apiBase}/input/${gameId}`, {
    method: "POST",
    body: JSON.stringify({ x, y }),
    headers: { "Content-Type": "application/json" },
  });
  const data: GameState = await res.json();
  return data;
}
