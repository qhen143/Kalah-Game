package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.board.IBoard;
import kalah.board.KalahBoardDefault;
import kalah.console.IConsoleDisplay;
import kalah.console.HorizontalDisplay;
import kalah.controller.IController;
import kalah.controller.KalahController;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {

		// Initilise game board and display
		IBoard board = new KalahBoardDefault();
		IConsoleDisplay cd = new HorizontalDisplay(io);
		IController ctrl = new KalahController(board, cd);

		ctrl.run();
	}
}
