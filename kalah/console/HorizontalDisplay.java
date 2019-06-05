package kalah.console;

import com.qualitascorpus.testsupport.IO;
import kalah.board.pit.Pit;
import kalah.board.player.Player;

import java.util.ArrayList;

public class HorizontalDisplay extends ConsoleDisplay {

    public HorizontalDisplay(IO io) {
        super(io);
    }

    public void displayBoard( ArrayList<Pit> pits, ArrayList<Player> players) {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        String player1Line = getStore(player2);
        String player2Line = getPlayerTile(player2);

        for (int pitIndex = 0; pitIndex < pits.size()/2-1; pitIndex++) {
            Pit pit = pits.get(pitIndex);
            Pit oppositePit = pits.get(pits.size()-1-1-pitIndex);

            player1Line = player1Line + getHouseDisplay(pit);
            player2Line = player2Line + getHouseDisplay(oppositePit);
        }

        player2Line = player2Line + getStore(player1) + "|";
        player1Line = player1Line + getPlayerTile(player1) + "|";

        io.println(player2Line);
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(player1Line);

        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    private String getHouseDisplay(Pit pit) {
        int seeds = pit.getNumSeeds();
        return "| " + pit.getPitID() + "[" + formatNumber(seeds) + "] ";
    }

    private String formatNumber(int value){
        return  String.format("%2d", value);
    }

    private String getPlayerTile(Player player) {
        return "| P" + player.getPlayerID()+" ";
    }

    private String getStore(Player player) {
        return "| "+ formatNumber(player.getScore())+" ";
    }

}
