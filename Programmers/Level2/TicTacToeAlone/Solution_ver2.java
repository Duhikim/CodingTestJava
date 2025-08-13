package CodingTestStudy.Level2.TicTacToeAlone;

public class Solution_ver2 {
	public int solution(String[] board) {
		int O = 0, X = 0;

		for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
				if(board[i].charAt(j) == 'O') O++;
				if(board[i].charAt(j) == 'X') X++;
		}
		if(O==X && !isMade('O', board)) return 1;
		if(O==X+1 && !isMade('X', board)) return 1;
		return 0;
	}

	public boolean isMade(char C, String[]board){
		String str = String.valueOf(C).repeat(3);
		for(int i=0; i<3; i++){
			if(board[i].equals(str)) return true;
			if(board[0].charAt(i) == C && board[1].charAt(i) == C && board[2].charAt(i) == C) return true;
		}
		if((board[0].charAt(0) == C && board[1].charAt(1) == C && board[2].charAt(2) == C) ||
		(board[0].charAt(2) == C && board[1].charAt(1) == C && board[2].charAt(0) == C)) return true;

		return false;
	}

}