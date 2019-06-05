package kalah.board.pit;

import kalah.board.player.Player;

public interface IPit {
    Player getPlayer();
    int getPitID();
    int getNumSeeds();
    void plantSeeds(int numSeeds);
}
