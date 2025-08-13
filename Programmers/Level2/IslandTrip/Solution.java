package CodingTestStudy.Level2.IslandTrip;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int row, col;
	public int[] solution(String[] maps) {

		char[][] mapsC = Arrays.stream(maps).map(String::toCharArray).toArray(char[][]::new);

		row = maps.length;
		col = maps[0].length();
		boolean[][] investigated = new boolean[row][col];

		ArrayList<Integer> answer = new ArrayList<>();

		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				if(!investigated[i][j] && mapsC[i][j] != 'X') {
					int[] sumOfIsle = {0};
					search(mapsC, investigated, sumOfIsle, i, j);
					answer.add(sumOfIsle[0]);
				}
			}
		}


		if(answer.size()==0) return new int[]{-1};
		return answer.stream().sorted().mapToInt(i -> i).toArray();
	}

	public void search(char[][] mapsC, boolean[][] investigated, int[] sumOfIsle, int i, int j){
		sumOfIsle[0] += (mapsC[i][j] - '0');
		investigated[i][j] = true;

		for(int[] dir: directions){
			int newI = i+dir[0];
			int newJ = j+dir[1];
			if(newI<0 || newJ < 0 || newI >= row || newJ >= col) continue;
			if(mapsC[newI][newJ] != 'X' && !investigated[newI][newJ]){
				search(mapsC, investigated, sumOfIsle, newI, newJ);
			}
		}
	}
}