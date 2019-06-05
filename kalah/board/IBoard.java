package kalah.board;

import kalah.board.pit.Pit;
import kalah.board.player.Player;

import java.util.ArrayList;

public interface IBoard {
    int executeTurn(int keyInput);
    boolean isGameOver();
    void updateFinalTotal();

    Player getCurrentPlayer();
    ArrayList<Pit> getPits();
    ArrayList<Player> getPlayers();

}
