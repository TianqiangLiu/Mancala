package Model;

import java.util.ArrayList;

import Game.GameControl;

public class Board {
	private ArrayList<Integer> board;
	static final int DEFAULT_INITIAL = 5;

	public Board() {
		board = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			board.add(DEFAULT_INITIAL);
			// board.add(i);
		}
	}

	public Board(int num) {
		board = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			board.add(num);
		}
	}

	public ArrayList<Integer> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<Integer> board) {
		this.board = board;
	}

	public boolean moveHole(boolean player, int num) {

		int numOfBall = board.get(num);
		board.set(num, 0);
		for (int i = 1; i <= numOfBall; i++) {
			int temPosition = (num + i) > 23 ? (num + i - 24) : (num + i);
			if (player && (temPosition == 6 || temPosition == 18)) {
				boolean bool = chooseOnOtherMancala();
				if (bool) {
					board.set(temPosition, board.get(temPosition) - 1);
					board.set(0, board.get(0) + 2);
				} else {
					numOfBall = numOfBall + 1;
				}
			} else if (!player && (temPosition == 0 || temPosition == 12)) {
				boolean bool = chooseOnOtherMancala();
				if (bool) {
					board.set(temPosition, board.get(temPosition) - 1);
					board.set(6, board.get(6) + 2);
				} else {
					numOfBall = numOfBall + 1;
				}
			} else {
				board.set(temPosition, board.get(temPosition) + 1);
				if (i == numOfBall && board.get(temPosition) == 1) {
					lastEmpty(temPosition, player);

				} else if (i == numOfBall && ((player && (temPosition == 0 || temPosition == 12))
						|| (player && (temPosition == 6 || temPosition == 18)))) {
					System.out.println("Your last stone is in your Mancalas. You play second round!!!");
					return false;
				}
			}
		}
		return true;
	}

	public boolean moveHolea1(boolean player, int num) {

		int numOfBall = board.get(num);
		board.set(num, 0);
		for (int i = 1; i <= numOfBall; i++) {
			int temPosition = (num + i) > 23 ? (num + i - 24) : (num + i);
			if (player && (temPosition == 6 || temPosition == 18)) {
				boolean bool = false;
				if (bool) {
					board.set(temPosition, board.get(temPosition) - 1);
					board.set(0, board.get(0) + 2);
				} else {
					numOfBall = numOfBall + 1;
				}
			} else if (!player && (temPosition == 0 || temPosition == 12)) {
				boolean bool = false;
				if (bool) {
					board.set(temPosition, board.get(temPosition) - 1);
					board.set(6, board.get(6) + 2);
				} else {
					numOfBall = numOfBall + 1;
				}
			} else {
				board.set(temPosition, board.get(temPosition) + 1);
				if (i == numOfBall && board.get(temPosition) == 1) {
					lastEmpty(temPosition, player);

				} else if (i == numOfBall && ((player && (temPosition == 0 || temPosition == 12))
						|| (player && (temPosition == 6 || temPosition == 18)))) {
					System.out.println("Your last stone is in your Mancalas. You play second round!!!");
					return false;
				}
			}
		}
		return true;
	}

	private void lastEmpty(int position, boolean player) {
		int sum = 0;
		if (position <= 3 || position >= 21 || (position >= 9 && position <= 15)) {
			sum = board.get(24 - position);
			board.set(24 - position, 0);
		} else if (position >= 4 && position <= 9) {
			sum = board.get(12 - position);
			board.set(12 - position, 0);
		} else {
			sum = board.get(36 - position);
			board.set(36 - position, 0);
		}
		int tem = player ? 0 : 6;
		board.set(tem, board.get(tem) + sum);
	}

	private boolean chooseOnOtherMancala() {
		System.out.println("Do you want to put a stone in opponent's Mancala to earn two stones?(1 for yes, 2 for no)");
		int tem = GameControl.scan.nextInt();

		return tem == 1;
	}

	public boolean valityHole(int holenum, Boolean player) {
		if (holenum > 23 || holenum < 0 || holenum == 0 || holenum == 6 || holenum == 12 || holenum == 18) {
			System.out.println("Cannot choose Mancala!");
			return false;
		}
		int numOfBall = board.get(holenum);
		if (numOfBall == 0) {
			System.out.println("Cannot choose empty hole!");
			return false;
		}
		if (player) {
			if ((holenum >= 3 && holenum <= 8) || (holenum >= 15 && holenum <= 20)) {
				System.out.println("Only allow to choose your side!");
				return false;
			}
		} else {
			if (holenum < 3 || holenum > 20 || (holenum >= 9 && holenum <= 14)) {
				System.out.println("Only allow to choose your side!");
				return false;
			}
		}

		return true;
	}

	public boolean finishCheck() {
		boolean win = true;
		for (int i = 1; i < 3; i++) {
			if (board.get(i) != 0) {
				win = false;
			}
		}
		for (int i = 9; i < 15; i++) {
			if (board.get(i) != 0 && i != 12) {
				win = false;
			}
		}
		for (int i = 21; i < 24; i++) {
			if (board.get(i) != 0) {
				win = false;
			}
		}
		if (win)
			return win;
		win = true;
		for (int i = 3; i < 9; i++) {
			if (board.get(i) != 0 && i != 6) {
				win = false;
			}
		}
		for (int i = 15; i < 21; i++) {
			if (board.get(i) != 0 && i != 18) {
				win = false;
			}
		}
		return win;
	}
	public boolean winCheck() {
		boolean win = true;
		for (int i = 1; i < 3; i++) {
			if (board.get(i) != 0) {
				win = false;
			}
		}
		for (int i = 9; i < 15; i++) {
			if (board.get(i) != 0 && i != 12) {
				win = false;
			}
		}
		for (int i = 21; i < 24; i++) {
			if (board.get(i) != 0) {
				win = false;
			}
		}
		if (win) {
			System.out.println("Player one Wins !!!!!");
			return win;
		}
		win = true;
		for (int i = 3; i < 9; i++) {
			if (board.get(i) != 0 && i != 6) {
				win = false;
			}
		}
		for (int i = 15; i < 21; i++) {
			if (board.get(i) != 0 && i != 18) {
				win = false;
			}
		}
		if (win) {
			System.out.println("Player two Wins !!!!!");
			return win;
		}
		return win;
	}

}
