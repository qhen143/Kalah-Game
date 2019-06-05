package kalah.board;

import kalah.board.pit.House;
import kalah.board.player.Player;

public class KalahBoardDefault extends KalahBoard {

    public KalahBoardDefault() {
        super();
    }

    // Game logic for the key inputs
    public int executeTurn(int keyInput) {
        if ((keyInput < 1) || (keyInput > 6)) {
            return -2;
        }
        // Obtains the index of the starting House based on the key input and the current Player.
        int currentPit = getIndexFromKeyInput(keyInput);

        // Obtains the seeds from the designated House to be sowed
        House startingHouse = (House) pits.get(currentPit);
        if (startingHouse.getNumSeeds() == 0) {
            return -1;
        }
        int seedsLeft = startingHouse.TakeSeeds();
        currentPit++;

        // Sow one seed in the current Pit or use it to capture the opposite House.
        while (seedsLeft > 0) {
            if (currentPit == pits.size()) {
                currentPit = 0;
            }

            // Either capture or sow a seed.
            boolean captured = captureSeeds(currentPit, seedsLeft);
            if (captured) {
                seedsLeft--;
            } else {
                seedsLeft -= sowSeed(currentPit);
            }

            // Changes turn when possible
            setNextPlayer(seedsLeft, currentPit);

            currentPit++;
        }
        return 0;
    }

    // Determines the player for the next turn
    private void setNextPlayer(int seedsLeft, int index) {
        if (seedsLeft == 0) {
            if (belongsToCurrentPlayer(index)) {
                if (!isStore(index)) {
                    currentPlayer = findNextPlayer();
                }
            } else {
                currentPlayer = findNextPlayer();
            }
        }
    }

    private boolean captureSeeds(int index, int seedsLeftToSow) {
        if ((pits.get(index).getNumSeeds() == 0) && (seedsLeftToSow == 1)) {
            if (isHouse(index) && (belongsToCurrentPlayer(index))) {
                House oppositeHouse = findOppositeHouse(index);

                if (oppositeHouse.getNumSeeds() > 0) {
                    int indexOfStore = (currentPlayer.getPlayerID() - 1) * numPitsPerPlayer + (numPitsPerPlayer - 1);
                    int captured = oppositeHouse.TakeSeeds();
                    pits.get(indexOfStore).plantSeeds(captured + 1);
                    currentPlayer.addPoints(captured + 1);
                    return true;
                }

            }
        }
        return false;
    }

    // Sows a seed if the current Pit is not the opponents Store.
    private int sowSeed(int currentPit) {
        if (!(isStore(currentPit)) || (belongsToCurrentPlayer(currentPit))) {
            pits.get(currentPit).plantSeeds(1);
            if (isStore(currentPit)) {
                pits.get(currentPit).getPlayer().addPoints(1);
            }
            return 1;
        }
        return 0;
    }

    // Game state logic for ending the game.
    public boolean isGameOver() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        int player1Seeds = getRemainingSeeds(player1);
        int player2Seeds = getRemainingSeeds(player2);

        // Ends the game when either player has no seeds left ON THEIR TURN.
        return ((player1Seeds == 0) && (currentPlayer == player1)) || (((player2Seeds == 0) && (currentPlayer == player2)));
    }
}
