package kalah.console;

import kalah.board.pit.Pit;
import kalah.board.player.Player;

import java.util.ArrayList;

public interface IConsoleDisplay {
    String getInputMessage(int playerId);

    void displayGameOver();
    void displayResults(ArrayList<Player> players);
    void displayInputError();
    void displayBoard(ArrayList<Pit> pits, ArrayList<Player> players);
}
