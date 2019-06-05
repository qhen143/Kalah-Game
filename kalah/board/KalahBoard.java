package kalah.board;

import kalah.board.pit.House;
import kalah.board.pit.Pit;
import kalah.board.pit.Store;
import kalah.board.player.Player;

import java.util.ArrayList;

import static kalah.GameConstants.*;

public abstract class KalahBoard implements IBoard{

    // Game Props
    Player currentPlayer;
    int numPitsPerPlayer;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Pit> pits = new ArrayList<>();

    KalahBoard() {
        this.numPitsPerPlayer = numHousesPerPlayer+numStoresPerPlayer;
        initBoard();
    }

    // Setting up props
    private void initBoard() {
        // For each Player, create their Houses and Stores.
        for (int playerID = 0; playerID < numPlayers; playerID++) {
            Player player = new Player(playerID+1);
            this.players.add(player);
            if (playerID == 0) {this.currentPlayer = player;}

            // Create Houses and assigns a Player and starting number of seeds to it.
            for (int houseID = 0; houseID < numHousesPerPlayer; houseID++) {
                this.pits.add(new House(player, houseID+1, startingSeedsForHouses));
            }

            // Create Stores and assigns a Player and starting number of seeds to it.
            for (int storeID = 0; storeID < numStoresPerPlayer; storeID++) {
                this.pits.add(new Store(player, storeID+1, startingSeedsForStores));
                player.addPoints(startingSeedsForStores);
            }
        }
    }

    public void updateFinalTotal() {
        for (Player player : players) {
            player.addPoints(getRemainingSeeds(player));
        }
    }

    // Getter Methods
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    int getIndexFromKeyInput(int keyInput) {
        return (keyInput-1) + this.numPitsPerPlayer*(this.currentPlayer.getPlayerID()-1);
    }

    // Shortcuts for common methods
    boolean isStore(int index) {
        return this.pits.get(index) instanceof Store;
    }

    boolean isHouse(int index) {
        return this.pits.get(index) instanceof House;
    }

    boolean belongsToCurrentPlayer(int index) {
        return this.pits.get(index).getPlayer() == this.currentPlayer;
    }

    // Given a House of interest, the House opposite is returned.
    House findOppositeHouse(int index) {
        int distanceToOpposingHouse = (numHousesPerPlayer)*2 - index;
        return (House)pits.get(distanceToOpposingHouse);
    }

    // Determines the next players for the following turn.
    Player findNextPlayer() {
        int nextPlayerID = this.currentPlayer.getPlayerID();
        if (nextPlayerID > numPlayers - 1) {
            nextPlayerID = 0;
        }
        return players.get(nextPlayerID);
    }

    // Given a player, it will return its corresponding seeds still in their House.
    int getRemainingSeeds(Player player) {
        int remainingSeeds = 0;
        for (int pitIndex = 0; pitIndex < pits.size(); pitIndex++) {
            if ((isHouse(pitIndex))&&(pits.get(pitIndex).getPlayer()==player)) {
                remainingSeeds += pits.get(pitIndex).getNumSeeds();
            }
        }
        return remainingSeeds;
    }
}
