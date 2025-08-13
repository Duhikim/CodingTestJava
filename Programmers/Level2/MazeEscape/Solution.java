package CodingTestStudy.Level2.MazeEscape;

// BFS로 두 번 하면 된다. S -> L, L -> E

import java.util.ArrayDeque;

public class Solution {
	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int solution(String[] maps) {
		int row = maps.length;
		int col = maps[0].length();
		int sR=-1, sC=-1, lR=-1, lC=-1, eR=-1, eC=-1; // start Row/Col, lever Row/Col, end Row/Col

		char[][] board = new char[row][col];

		for(int i=0; i<row; i++)for(int j=0; j<col; j++){

			if(maps[i].charAt(j) == 'S') {sR = i; sC = j;}
			else if(maps[i].charAt(j) == 'L') {lR = i; lC = j;}
			else if(maps[i].charAt(j) == 'E') {eR = i; eC = j;}

			if(maps[i].charAt(j) == 'X') board[i][j] = 'X';
			else board[i][j] = 'O';
		}

		int stol = setSteps(board, sR, sC, lR, lC);
		int stoe = setSteps(board, lR, lC, eR, eC);

		return (stol != -1 && stoe != -1)? stol+stoe : -1;
	}

	public int setSteps(char[][] board, int sR, int sC, int eR, int eC){

		int step = 0;

		ArrayDeque<int[]> workDeque = new ArrayDeque<>();
		workDeque.add(new int[]{sR, sC});
		int row = board.length;
		int col = board[0].length;

		int[][] steps = new int[row][col];
		for(int i=0; i<row; i++)for(int j=0; j<col; j++){ steps[i][j] = -1; }
		steps[sR][sC] = step;

		while(!workDeque.isEmpty()){
			step++;

			int size = workDeque.size();

			for(int i=0; i<size; i++){
				int[] curr = workDeque.poll();
				for(int[] dir: directions){
					int newR = curr[0] + dir[0];
					int newC = curr[1] + dir[1];
					if(newR < 0 || newR >= row || newC < 0 || newC >= col ||
							board[newR][newC] == 'X' || steps[newR][newC] != -1) continue;
					steps[newR][newC] = step;
					workDeque.add(new int[]{newR, newC});
				}
			}

			if(steps[eR][eC] != -1) return steps[eR][eC];
		}
		return -1;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] maps = 	{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
		sol.solution(maps);
	}
}