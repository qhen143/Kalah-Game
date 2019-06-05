package kalah.console;

import com.qualitascorpus.testsupport.IO;
import kalah.board.player.Player;

import java.util.ArrayList;

public abstract class ConsoleDisplay implements IConsoleDisplay{
    IO io;

    ConsoleDisplay(IO io) {
        this.io = io;
    }

    public String getInputMessage(int playerId) {
        String message = "Player P" + playerId + "'s turn - Specify house number or 'q' to quit: ";
        return io.readFromKeyboard(message);
    }

    public void displayGameOver() {
        io.println("Game over");
    }

    public void displayResults(ArrayList<Player> players) {
        int winner = 0;
        int max = 0;
        boolean tie = false;

        for (Player player : players) {
            int finalScore = (player.getScore());
            if (finalScore > max) {
                max = finalScore;
                winner = player.getPlayerID();

            } else if (finalScore == max) {
                tie = true;
            }
            io.println("\tplayer " + player.getPlayerID() + ":" + finalScore);
        }

        if (tie) {
            io.println("A tie!");

        } else {
            io.println("Player " + winner + " wins!");
        }
    }

    public void displayInputError() {
        io.println("House is empty. Move again.");
    }

}
