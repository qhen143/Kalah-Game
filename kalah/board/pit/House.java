package kalah.board.pit;

import kalah.board.player.Player;

public class House extends Pit{

    public House() {}

    public House(Player player, int houseID, int startingSeeds) {
        super(player, houseID, startingSeeds);
    }

    // Gives away its seeds
    public int TakeSeeds() {
        int seedsAvailable = getNumSeeds();
        this.numSeeds = 0;
        return seedsAvailable;
    }
}
