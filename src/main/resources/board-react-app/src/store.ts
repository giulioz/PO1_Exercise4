import create from "zustand";
import { devtools } from "zustand/middleware";
import {
  advanceGame,
  GameState,
  getBoardTypes,
  getGameState,
  getPlayerTypes,
  giveInputGame,
  postNewGame,
} from "./api";

export const pages = ["SELECT_PLAYER", "GAME"];

export type StateType = {
  currentPage: typeof pages[0];
  setCurrentPage(currentPage: typeof pages[0]): void;

  playerClasses: string[] | null;
  requirePlayerClasses(): Promise<void>;
  boardClasses: string[] | null;
  requireBoardClasses(): Promise<void>;

  playerAClass: string | null;
  setPlayerBClass(c: string): void;
  playerBClass: string | null;
  setPlayerAClass(c: string): void;
  boardType: string | null;
  setBoardType(c: string): void;

  gameId: string | null;
  lastGameState: GameState | null;
  gameState: GameState | null;
  needsInput: boolean;
  startGame(): Promise<void>;
  nextMove(): Promise<void>;
  doInput(x: number, y: number): Promise<void>;
};

export function requireValue<T>(
  selector: (s: StateType) => T | null,
  requiredValue: T | null,
): asserts requiredValue is T {
  if (requiredValue === null || selector(useStore.getState()) === null) {
    throw new Promise<void>((resolve) => {
      const unsub = useStore.subscribe((s) => {
        if (s && selector(s)) {
          unsub();
          resolve();
        }
      });
    });
  }
}

export const useStore = create<StateType>(
  devtools((set, get) => ({
    currentPage: "SELECT_PLAYER",
    setCurrentPage(currentPage: typeof pages[0]) {
      set((s) => ({ ...s, currentPage }));
    },

    playerClasses: null,
    async requirePlayerClasses() {
      const playerClasses = await getPlayerTypes();
      set((s) => ({
        ...s,
        playerClasses,
        playerAClass: playerClasses[0],
        playerBClass: playerClasses[0],
      }));
    },
    boardClasses: null,
    async requireBoardClasses() {
      const boardClasses = await getBoardTypes();
      set((s) => ({
        ...s,
        boardClasses,
        boardType: boardClasses[0],
      }));
    },

    playerAClass: null,
    setPlayerAClass(playerAClass: string) {
      set((s) => ({ ...s, playerAClass }));
    },
    playerBClass: null,
    setPlayerBClass(playerBClass: string) {
      set((s) => ({ ...s, playerBClass }));
    },
    boardType: null,
    setBoardType(boardType: string) {
      set((s) => ({ ...s, boardType }));
    },

    gameId: null,
    gameState: null,
    lastGameState: null,
    needsInput: false,

    async startGame() {
      const state = get();
      if (state.playerAClass && state.playerBClass && state.boardType) {
        const gameId = await postNewGame(
          state.playerAClass,
          state.playerBClass,
          state.boardType,
        );
        const gameState = await getGameState(gameId);
        set((s) => ({
          ...s,
          currentPage: "GAME",
          gameId,
          lastGameState: gameState,
          gameState,
        }));
        await get().nextMove();
      }
    },

    async nextMove() {
      const state = get();
      if (state.gameId) {
        try {
          const gameState = await advanceGame(state.gameId);
          if (gameState === "NEEDS_INPUT") {
            set((s) => ({
              ...s,
              currentPage: "GAME",
              lastGameState: s.gameState,
              needsInput: !state.gameState?.winner,
            }));
          } else {
            set((s) => ({
              ...s,
              lastGameState: s.gameState,
              gameState,
              needsInput: false,
            }));
          }
        } catch {
          set((s) => ({
            ...s,
            lastGameState: s.gameState,
          }));
        }
      }
    },

    async doInput(x: number, y: number) {
      const state = get();
      if (state.gameId && state.needsInput) {
        const gameState = await giveInputGame(state.gameId, x, y);
        set((s) => ({
          ...s,
          gameState,
          needsInput: false,
        }));
        await get().nextMove();
      }
    },
  })),
);
