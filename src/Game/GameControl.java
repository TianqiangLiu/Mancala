package Game;

import java.util.Scanner;

import AI.AIStrategy1;
import AI.AIStrategy2;
import Model.Board;
import View.BoardView;

public class GameControl {
	Board board;
	BoardView boardView;
	boolean player;
	public static Scanner scan = new Scanner(System.in);
	AIStrategy1 a1;
	AIStrategy2 a2;

	public GameControl() {
		board = new Board();
		boardView = new BoardView(board);
		player = true;
		a1 = new AIStrategy1(player, board);
		
		start();
	}

	public GameControl(String s[]) {

		board = new Board(Integer.parseInt(s[1]));
		boardView = new BoardView(board);
		player = true;
		a1 = new AIStrategy1(player, board);
		a2 = new AIStrategy2(player, board);
		if (s[0].equalsIgnoreCase("a1")) {
			startA1();
		}
		else if (s[0].equalsIgnoreCase("a1a2")) {
			startA1A2();
		}
		else if (s[0].equalsIgnoreCase("a1a2r")) {
			startA1A2Random();
		}else if (s[0].equalsIgnoreCase("a2")) {
			startA2();
		}
	}

	public void startA1() {
		boardView.drawBoard();

		while (!board.finishCheck()) {
			String playername = player ? "Player1" : "Player2";
			boolean holeVality = false;
			int holenum = 0;
			while (!holeVality) {
				if (player) {
					System.out.println("Hello " + playername + ", Please choose hole to move");
					//System.out.println("AI Suggestion: " + a1.makeMove(player));
					holenum = scan.nextInt();
					holeVality = board.valityHole(holenum, player);
				}
				else {
					holenum = a1.makeMove(player);
					System.out.println("AI Searched Nodes Number is : "+ a1.getSearchedNodeNumber());
					System.out.println("AI move: " + holenum);
					holeVality = true;
				}
			}
			boolean treat;
			if(player) {
				treat = board.moveHole(player, holenum);
			}
			else {
				treat = board.moveHolea1(player, holenum);
			}

			player = treat ? !player : player;
			boardView.drawBoard();
		}
		board.winCheck();
	}


	public void startA2() {
		boardView.drawBoard();

		while (!board.finishCheck()) {
			String playername = player ? "Player1" : "Player2";
			boolean holeVality = false;
			int holenum = 0;
			while (!holeVality) {
				if (player) {
					System.out.println("Hello " + playername + ", Please choose hole to move");
					//System.out.println("AI Suggestion: " + a1.makeMove(player));
					holenum = scan.nextInt();
					holeVality = board.valityHole(holenum, player);
				}
				else {
					holenum = a2.makeMove(player);
					System.out.println("AI Searched Nodes Number is : "+ a2.getSearchedNodeNumber());
					System.out.println("AI move: " + holenum);
					holeVality = true;
				}
			}
			boolean treat;
			if(player) {
				treat = board.moveHole(player, holenum);
			}
			else {
				treat = board.moveHolea1(player, holenum);
			}

			player = treat ? !player : player;
			boardView.drawBoard();
		}
		board.winCheck();
	}
	public void startA1A2Random() {
		boardView.drawBoard();

		while (!board.finishCheck()) {
			String playername = player ? "Player1" : "Player2";
			boolean holeVality = false;
			int holenum = 0;
			while (!holeVality) {
				int ran = (int)(Math.random()*50+1);
				if (player) {
					System.out.println("Hello " + playername + ", Please choose hole to move");
					holenum = ran>=25?a1.makeMove(player):a2.makeMove(player);
					System.out.println("AI Searched Nodes Number is : "+ (ran>=25?a1.getSearchedNodeNumber():a2.getSearchedNodeNumber()));
					System.out.println("AI"+ (ran>=25?"1":"2")+" move: " + holenum);
					holeVality = true;
				}
				else {
					System.out.println("Hello " + playername + ", Please choose hole to move");
					holenum = ran>=25?a1.makeMove(player):a2.makeMove(player);
					System.out.println("AI Searched Nodes Number is : "+ (ran>=25?a1.getSearchedNodeNumber():a2.getSearchedNodeNumber()));
					System.out.println("AI"+ (ran>=25?"1":"2")+" move: " + holenum);
					holeVality = true;
				}
			}
			boolean treat;
			treat = board.moveHolea1(player, holenum);

			player = treat ? !player : player;
			boardView.drawBoard();
		}
		board.winCheck();
	}
	public void startA1A2() {
		boardView.drawBoard();

		while (!board.finishCheck()) {
			String playername = player ? "Player1" : "Player2";
			boolean holeVality = false;
			int holenum = 0;
			while (!holeVality) {
				if (player) {
					System.out.println("Hello " + playername + ", Please choose hole to move");
					holenum = a1.makeMove(player);
					System.out.println("AI Searched Nodes Number is : "+ a1.getSearchedNodeNumber());
					System.out.println("AI move: " + holenum);
					holeVality = true;
				}
				else {
					System.out.println("Hello " + playername + ", Please choose hole to move");
					holenum = a2.makeMove(player);
					System.out.println("AI Searched Nodes Number is : "+ a2.getSearchedNodeNumber());
					System.out.println("AI move: " + holenum);
					holeVality = true;
				}
			}
			boolean treat;
			treat = board.moveHolea1(player, holenum);

			player = treat ? !player : player;
			boardView.drawBoard();
		}
		board.winCheck();
	}

	public void start() {
		boardView.drawBoard();

		while (!board.finishCheck()) {
			String playername = player ? "Player1" : "Player2";
			boolean holeVality = false;
			int holenum = 0;
			while (!holeVality) {
				System.out.println("Hello " + playername + ", Please choose hole to move");
				System.out.println("AI Suggestion: " + a1.makeMove(player));
				holenum = scan.nextInt();
				holeVality = board.valityHole(holenum, player);
			}

			boolean treat = board.moveHole(player, holenum);

			player = treat ? !player : player;
			boardView.drawBoard();
		}
	}
}
