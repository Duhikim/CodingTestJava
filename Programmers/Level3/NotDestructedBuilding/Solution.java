package CodingTestStudy.Level3.NotDestructedBuilding;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        int[][] dmgBoard = new int[N][M];
        for(int[] s: skill){
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int value = s[5];
            if(s[0]==1) value *= -1;

            dmgBoard [r1][c1] += value;
            if(c2+1 < M) dmgBoard [r1][c2+1] -= value;
            if(r2+1 < N) dmgBoard [r2+1][c1] -= value;
            if(c2+1<M && r2+1<N) dmgBoard [r2+1][c2+1] += value;
        }

        for(int i=0; i<N; i++) for(int j=1; j<M; j++){
            dmgBoard [i][j] += dmgBoard [i][j-1];
        }
        for(int i=1; i<N; i++) for(int j=0; j<M; j++){
            dmgBoard [i][j] += dmgBoard [i-1][j];
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                if(board[i][j] + dmgBoard [i][j] > 0) answer++;
            }
        }

        return answer;
    }
}