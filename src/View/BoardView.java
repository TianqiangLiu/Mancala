package View;

import java.util.ArrayList;

import Model.Board;
import Utility.Utility;

public class BoardView {
	Board board;
	public BoardView(Board board) {
		this.board = board;
	}
	public void drawBoard() {
		ArrayList<Integer> boardArr= new ArrayList<Integer>(board.getBoard());
		System.out.println(Utility.getStringTemplate(boardArr));
	}

}
