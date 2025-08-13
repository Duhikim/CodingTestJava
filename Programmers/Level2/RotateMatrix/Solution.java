package CodingTestStudy.Level2.RotateMatrix;

import java.lang.Math;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {

        int[][] field = new int[rows][columns];
        int idx = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                field[i][j] = idx++;
            }
        }

        int n = queries.length;
        int[] answer = new int[n];

        for(int i=0; i<n; i++){
            answer[i] = Integer.MAX_VALUE;
            int sr = queries[i][0]-1;
            int sc = queries[i][1]-1;
            int er = queries[i][2]-1;
            int ec = queries[i][3]-1;

            int temp = field[sr][sc];
            for(int j=sr; j<er; j++){
                answer[i] = Math.min(field[j][sc] = field[j+1][sc], answer[i]);
            }
            for(int j=sc; j<ec; j++){
                answer[i] = Math.min(field[er][j] = field[er][j+1], answer[i]);
            }
            for(int j=er; j>sr; j--){
                answer[i] = Math.min(field[j][ec] = field[j-1][ec], answer[i]);
            }
            for(int j=ec; j>sc+1; j--){
                answer[i] = Math.min(field[sr][j] = field[sr][j-1], answer[i]);
            }
            answer[i] = Math.min(field[sr][sc+1] = temp, answer[i]);

        }

        return answer;
    }
}