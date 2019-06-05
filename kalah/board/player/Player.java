package kalah.board.player;

public class Player implements IPlayer{

    private int  playerID;
    private int score = 0;

    public Player(int id) {
        this.playerID = id;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score += points;
    }
}
