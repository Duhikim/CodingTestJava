package CodingTestStudy.Level3.MovingKart;
/*
격자의 최대 크기가 4by4밖에 안되므로 dfs를 쓰면 될것같음
*/
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

class Solution {
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1}; // 상 하 좌 우

    int r, c;
    int[][] maze;
    boolean[][] visitR;
    boolean[][] visitB;
    int[] sR, sB, eR, eB;
    int answer = 0;

    public int solution(int[][] maze) {

        this.maze = maze;
        this.r = maze.length;
        this.c = maze[0].length;
        visitR = new boolean[r][c];
        visitB = new boolean[r][c];

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(maze[i][j]==1){
                    sR = new int[]{i, j};
                    visitR[i][j] = true;
                } else if(maze[i][j]==2){
                    sB = new int[]{i, j};
                    visitB[i][j] = true;
                } else if(maze[i][j]==3){
                    eR = new int[]{i, j};
                } else if(maze[i][j]==4){
                    eB = new int[]{i, j};
                }
            }
        }
        dfs(sR[0], sR[1], sB[0], sB[1], 0);

        return answer;
    }

    public void dfs(int rR, int rC, int bR, int bC, int steps){
        if(rR==eR[0] && rC==eR[1] && bR==eB[0] && bC==eB[1]){
            if(answer==0 || answer > steps) answer = steps;
            return;
        }

        for(int i=0; i<4; i++){
            int nRR, nRC;
            if(rR==eR[0] && rC==eR[1]){
                nRR = rR;
                nRC = rC;
                i=4;
            }else{
                nRR = rR + dr[i];
                nRC = rC + dc[i];
                if(nRR < 0 || nRR >= r || nRC < 0 || nRC >= c) continue; // 필드 벗어남
                if(maze[nRR][nRC]==5 || visitR[nRR][nRC]) continue; // 벽 또는 방문한 곳.
            }
            for(int j=0; j<4; j++){
                int nBR, nBC;
                if(bR==eB[0] && bC==eB[1]){
                    nBR = bR;
                    nBC = bC;
                    j=4;
                }else{
                    nBR = bR + dr[j];
                    nBC = bC + dc[j];
                    if(nBR < 0 || nBR >= r || nBC < 0 || nBC >= c) continue;
                    if(maze[nBR][nBC]==5 || visitB[nBR][nBC]) continue;
                    if(nBR==rR && nBC==rC && nRR==bR && nRC==bC) continue; // 서로 위치가 바뀜
                }
                if(nRR==nBR && nRC==nBC) continue; // 같은 칸에 도착

                visitR[nRR][nRC] = true;
                visitB[nBR][nBC] = true;

                dfs(nRR, nRC, nBR, nBC, steps+1);

                visitR[nRR][nRC] = false;
                visitB[nBR][nBC] = false;
            }
        }

    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<int[][]> mazes = new ArrayList<>();
        mazes.add(new int[][]{{1, 4}, {0, 0}, {2, 3}});
        mazes.add(new int[][]{{1, 0, 2}, {0, 0, 0}, {5, 0 ,5}, {4, 0, 3}});
        mazes.add(new int[][]{{1, 5}, {2, 5}, {4, 5}, {3, 5}});
        mazes.add(new int[][]{{4, 1, 2, 3}});

        System.out.println(sol.solution(mazes.get(2)));

    }
}