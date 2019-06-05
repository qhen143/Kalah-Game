package kalah.controller;

import kalah.board.IBoard;
import kalah.console.IConsoleDisplay;

public class KalahController implements IController{
    private IBoard board;
    private IConsoleDisplay printer;

    public KalahController(IBoard board, IConsoleDisplay printer) {
        this.board = board;
        this.printer = printer;
    }

    public void run() {
        // Initial game state displayed
        printer.displayBoard(board.getPits(), board.getPlayers());

        String input;
        while (true) {
            if (board.isGameOver()) {
                gameOver();
                break;
            }

            // Key input to interpreted for game logic.
            input = printer.getInputMessage(board.getCurrentPlayer().getPlayerID());

            // Quit
            if (input.equals("q")) {
                quit();
                break;

            // Game Logic
            } else if (board.executeTurn(Integer.parseInt(input))==-1) {
                inputError();
                continue;
            }

            printer.displayBoard(board.getPits(), board.getPlayers());
        }
    }

    public void gameOver() {
        quit();
        board.updateFinalTotal();
        printer.displayResults(board.getPlayers());
    }

    public void quit() {
        printer.displayGameOver();
        printer.displayBoard(board.getPits(), board.getPlayers());
    }

    public void inputError() {
        printer.displayInputError();
        printer.displayBoard(board.getPits(), board.getPlayers());
    }


}
