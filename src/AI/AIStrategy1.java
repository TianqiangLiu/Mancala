package AI;

import java.util.ArrayList;

import Model.Board;

public class AIStrategy1 {
	private boolean player;
	private Board board;
	private int depth;

	public AIStrategy1(boolean player, Board board) {
		this.player = player;
		this.board = board;
		this.depth = 3;
	}

	public int makeMove(boolean player) {
		this.player = player;
		ArrayList<Integer> movesArrayList = new ArrayList<Integer>(getMoves(player, board.getBoard()));
		
		int sum = Integer.MIN_VALUE;
		int position = -1;
		for (int i = 0; i < movesArrayList.size(); i++) {
			ArrayList<Integer> arr= new ArrayList<Integer>(moveArr(board.getBoard(),player,movesArrayList.get(i)));
			int tem = minValue(arr,!player,this.depth);
			if(sum<tem) {
				sum = tem;
				position = movesArrayList.get(i);
			}
		}

		huristics(board.getBoard(), player);

		return position;
	}
	
	private int minValue(ArrayList<Integer> arr, boolean player, int i) {
		
		if(i==0) {
			return huristics(arr,this.player);
		}else {
			ArrayList<Integer> movesArrayList = new ArrayList<Integer>(getMoves(player, arr));
			int sum = Integer.MIN_VALUE;
			for (int j = 0; j < movesArrayList.size(); j++) {
				ArrayList<Integer> arr1= new ArrayList<Integer>(moveArr(arr,player,movesArrayList.get(j)));
				int tem = maxValue(arr1,!player,i-1);
				if(sum<tem) {
					sum = tem;
				}
			}
			return sum;
		}
	}
	private int maxValue(ArrayList<Integer> arr, boolean player, int i) {
		
		if(i==0) {
			return huristics(arr,this.player);
		}else {
			ArrayList<Integer> movesArrayList = new ArrayList<Integer>(getMoves(player, arr));
			int sum = Integer.MIN_VALUE;
			for (int j = 0; j < movesArrayList.size(); j++) {
				ArrayList<Integer> arr1= new ArrayList<Integer>(moveArr(arr,player,movesArrayList.get(j)));
				int tem = minValue(arr1,!player,i-1);
				if(sum<tem) {
					sum = tem;
				}
			}
			return sum;
		}
	}

	private ArrayList<Integer> moveArr(ArrayList<Integer> board1, boolean player, Integer num) {
		ArrayList<Integer> board= new ArrayList<Integer>(board1);
		int numOfBall = board.get(num);
		board.set(num, 0);
		for (int i = 1; i <= numOfBall; i++) {
			int temPosition = (num+i)>23?(num+i-24):(num+i);
			if(player&&(temPosition==6||temPosition==18)) {
				boolean bool = false;
				if(bool) {
					board.set(temPosition, board.get(temPosition)-1);
					board.set(0, board.get(0)+2);
				}else {
					numOfBall=numOfBall+1;
				}
			}
			else if(!player&&(temPosition==0||temPosition==12)) {
				boolean bool = false;
				if(bool) {
					board.set(temPosition, board.get(temPosition)-1);
					board.set(6, board.get(6)+2);
				}else {
					numOfBall=numOfBall+1;
				}
			}else {
				board.set(temPosition, board.get(temPosition)+1);
				if(i==numOfBall&&board.get(temPosition)==1) {
					lastEmpty(temPosition, player,board);
					
				}else if (i==numOfBall&&((player&&(temPosition==0||temPosition==12))||(player&&(temPosition==6||temPosition==18)))) {
					//System.out.println("Your last stone is in your Mancalas. You play second round!!!");
				}
			}
		}
		return board;
	}
	private void lastEmpty(int position, boolean player, ArrayList<Integer>board) {
		int sum = 0;
		if(position<=3||position>=21||(position>=9&&position<=15)) {
			sum = board.get(24-position);
			board.set(24-position, 0);
		}else if(position>=4&&position<=9){
			sum = board.get(12-position);
			board.set(12-position, 0);
		}else {
			sum = board.get(36-position);
			board.set(36-position, 0);
		}
		int tem = player?0:6;
		board.set(tem, board.get(tem)+sum);
	}

	private ArrayList<Integer> getMoves(boolean player, ArrayList<Integer> board) {
		ArrayList<Integer> map = new ArrayList<Integer>();
		if (player) {

			for (int i = 1; i < 3; i++) {
				if (board.get(i) != 0) {
					map.add(i);
				}
			}
			for (int i = 9; i < 15; i++) {
				if (board.get(i) != 0 && i != 12) {
					map.add(i);
				}
			}
			for (int i = 21; i < 24; i++) {
				if (board.get(i) != 0) {
					map.add(i);
				}
			}
		} else {

			for (int i = 3; i < 9; i++) {
				if (board.get(i) != 0 && i != 6) {
					map.add(i);
				}
			}
			for (int i = 15; i < 21; i++) {
				if (board.get(i) != 0 && i != 18) {
					map.add(i);
				}
			}
		}
		return map;
	}

	private int huristics(ArrayList<Integer> arrayList, boolean player) {
		ArrayList<Integer> arr = new ArrayList<Integer>(arrayList);
		int sum = 0;
		if (this.player) {
			for (int i = 0; i < 3; i++) {
				sum = sum + arr.get(i);
			}
			for (int i = 9; i < 15; i++) {
				sum = sum + arr.get(i);
			}
			for (int i = 21; i < 24; i++) {
				sum = sum + arr.get(i);
			}
		} else {
			for (int i = 3; i < 9; i++) {
				sum = sum + arr.get(i);
			}
			for (int i = 15; i < 21; i++) {
				sum = sum + arr.get(i);
			}
		}
		return sum;
	}

}
