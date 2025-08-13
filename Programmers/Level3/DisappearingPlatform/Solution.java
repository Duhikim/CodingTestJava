package CodingTestStudy.Level3.DisappearingPlatform;

import java.lang.Math;

// 현재 상태에서 주체가 움직이면서 끝나면 주체의 승
// 혹은 현재 상태에서 홀수스탭에 경기가 끝나면 주체 승
// 짝수 스탭에 경기가 끝나면 주체 패.
// 재귀하여 넘어온 결과 중 홀수와 짝수가 섞여 있으면 홀수 선택
// 결과가 홀수가 여러개면 가장 작은값 선택
// 결과가 모두 짝수면 가장 큰값 선택

class Solution {
    int[]dr = {0, 0, 1, -1};
    int[]dc = {1, -1, 0, 0}; // R, L, D, U
    int r, c;

    int[][] board;
    public int solution(int[][] board, int[] aloc, int[] bloc) {

        this.board = board;
        this.r = board.length;
        this.c = board[0].length;

        int answer = dfs(aloc, bloc);
        return answer;
    }

    public int dfs(int[] subject, int[] object){
        // 주체가 서있는 곳의 발판이 0이면 패배. 0을 반환
        if(board[subject[0]][subject[1]]==0) return 0;

        int result = 0;
        for(int i=0; i<4; i++){
            int nr = subject[0] + dr[i];
            int nc = subject[1] + dc[i];
            if(nr<0 || nr>=r || nc < 0 || nc >= c || board[nr][nc]==0) continue;
            board[subject[0]][subject[1]] = 0;
            int newValue = dfs(object, new int[]{nr, nc}) + 1;
            board[subject[0]][subject[1]] = 1;

            // val과 result가 홀/짝 이면 홀을 선택
            // 짝/짝이면 큰걸 선택
            // 홀/홀 이면 작은걸 선택
            if((newValue&1)==0 && (result&1)==0) result = Math.max(result, newValue);
            else if ((newValue&1)==1 && (result&1)==0) result = newValue;
            else if ((newValue&1)==1 && (result&1)==1) result = Math.min(result, newValue);
            // else result = result;
        }
        return result;
    }
}