package kalah.board.pit;

import kalah.board.player.Player;

public abstract class Pit implements IPit{
    private Player player;
    private int pitID;
    protected int numSeeds;

    public Pit() {}

    public Pit(Player player, int pitID, int startingSeeds) {
        this.player = player;
        this.pitID = pitID;
        this.numSeeds = startingSeeds;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getPitID() {
        return this.pitID;
    }

    public int getNumSeeds() {
        return this.numSeeds;
    }

    public void plantSeeds(int numSeeds) {
        this.numSeeds += numSeeds;
    }
}
