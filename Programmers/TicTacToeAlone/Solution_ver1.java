package CodingTestStudy.TicTacToeAlone;

public class Solution_ver1 {
	public int solution(String[] board) {
		int O = 0, X = 0;

		for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
				if(board[i].charAt(j) == 'O') O++;
				if(board[i].charAt(j) == 'X') X++;
		}

		if(X > O || X < O-1) return 0;

		//안되는 경우.
		// 1. O와 X가 같은데 O가 메이드인 경우.
		// 2. O가 X보다 하나 더 많은데 X가 메이드인 경우.
		boolean xMade = false, oMade = false;
		for(int i=0; i<3; i++) {
			if(board[i].equals("XXX")) {
				xMade = true;
			}
			else if(board[i].equals("OOO")) {
				oMade = true;
			}
			else if(board[0].charAt(i) != '.' &&
					board[0].charAt(i) == board[1].charAt(i) &&
					board[0].charAt(i) == board[2].charAt(i)) {
				if(board[0].charAt(i) == 'X') xMade = true;
				else oMade = true;
			}
		}
		if(board[0].charAt(0) != '.' &&
				board[0].charAt(0) == board[1].charAt(1) &&
				board[0].charAt(0) == board[2].charAt(2)){
			if(board[0].charAt(0) == 'X') xMade = true;
			else oMade = true;
		}
		if(board[0].charAt(2) != '.' &&
				board[0].charAt(2) == board[1].charAt(1) &&
				board[0].charAt(2) == board[2].charAt(0)){
			if(board[0].charAt(2) == 'X') xMade = true;
			else oMade = true;
		}
		if(O==X && oMade) return 0;
		if(O==X+1 && xMade) return 0;

		return 1;
	}

}