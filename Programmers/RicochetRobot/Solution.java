package CodingTestStudy.RicochetRobot;

import java.util.ArrayDeque;

public class Solution {
	public int solution(String[] board) {

		int n = board.length;
		int m = board[0].length();
		int[][] steps = new int[n][m];
		for(int i=0; i<n; i++)for(int j=0; j<m; j++) steps[i][j] = -1;
		int startR=-1, startC=-1;
		int endR=-1, endC=-1;

		for(int i=0; i<n; i++)for(int j=0; j<m; j++){
			if(board[i].charAt(j)=='R') { startR = i; startC = j;}
			else if(board[i].charAt(j)=='G') { endR = i; endC = j;}
			if(startR != -1 && endR != -1) break;
		}

		if(endR != 0 && endC != 0 && endR != n-1 && endC != m-1
			&& board[endR-1].charAt(endC) != 'D' && board[endR].charAt(endC-1) != 'D'
			&& board[endR+1].charAt(endC) != 'D' && board[endR].charAt(endC+1) != 'D') return -1;

		setSteps(steps, board, startR, startC, endR, endC);

		return steps[endR][endC];
	}

	public void setSteps(int[][] steps, String[] board, int startR, int startC, int endR, int endC){
		// 시작점에서 상/하/좌/우 끝까지 가서 스텝수를 체크할 것.

		int n = board.length;
		int m = board[0].length();

		int step = 0;
		steps[startR][startC] = 0;

		ArrayDeque<int[]> stepDeque = new ArrayDeque<>();
		stepDeque.add(new int[]{startR, startC});

		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // 상, 하, 좌, 우

		while(!stepDeque.isEmpty()) {

			step++;

			int size = stepDeque.size();

			for (int i = 0; i < size; i++) {

				int[] curr = stepDeque.poll();

				for(int[] dir : directions){
					int currR = curr[0];
					int currC = curr[1];

					while (true) {
						int newR = currR + dir[0];
						int newC = currC + dir[1];

						if(newR < 0 || newR >= n || newC < 0 || newC >= m || board[newR].charAt(newC) == 'D') break;

						currR = newR;
						currC = newC;
					}

					if(steps[currR][currC] == -1){
						steps[currR][currC] = step;
						stepDeque.add(new int[]{currR, currC});
					}
					if (steps[endR][endC] != -1) return;
				}
			}
		}
	}
}