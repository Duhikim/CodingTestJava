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

		while(!stepDeque.isEmpty()) {

			step++;

			int size = stepDeque.size();

			for (int i = 0; i < size; i++) {

				int[] curr = stepDeque.poll();

				// UP
				int newR = curr[0], newC = curr[1];
				while (true) {
					if (newR == 0 || board[newR - 1].charAt(newC) == 'D') {
						if (steps[newR][newC] == -1) {
							steps[newR][newC] = step;
							stepDeque.add(new int[]{newR, newC});
						}
						break;
					}
					newR--;
				}

				// DOWN
				newR = curr[0];
				newC = curr[1];
				while (true) {
					if (newR == n - 1 || board[newR + 1].charAt(newC) == 'D') {
						if (steps[newR][newC] == -1) {
							steps[newR][newC] = step;
							stepDeque.add(new int[]{newR, newC});
						}
						break;
					}
					newR++;
				}

				// LEFT
				newR = curr[0];
				newC = curr[1];
				while (true) {
					if (newC == 0 || board[newR].charAt(newC - 1) == 'D') {
						if (steps[newR][newC] == -1) {
							steps[newR][newC] = step;
							stepDeque.add(new int[]{newR, newC});
						}
						break;
					}
					newC--;
				}

				// RIGHT
				newR = curr[0];
				newC = curr[1];
				while (true) {
					if (newC == m - 1 || board[newR].charAt(newC + 1) == 'D') {
						if (steps[newR][newC] == -1) {
							steps[newR][newC] = step;
							stepDeque.add(new int[]{newR, newC});
						}
						break;
					}
					newC++;
				}
				if (steps[endR][endC] != -1) return;
			}
		}
	}

}