package CodingTestStudy.LightPathCycle;

import java.util.*;
import java.lang.Math;

class Solution2 {
    String[] grid;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
    int h, w;
    boolean[][][] usedDir;

    public int[] solution(String[] grid) {
        this.grid = grid;
        h = grid.length;
        w = grid[0].length();
        usedDir = new boolean[h][w][4];

        List<Integer> answer = new ArrayList<>();

        for(int i=0; i<h; i++){for(int j=0; j<w; j++){
            for(int dir=0; dir<4; dir++){
                int cycle = makeCycle(i, j, dir);
                if(cycle != 0) answer.add(cycle);
            }
        }}

        return answer.stream().sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int makeCycle(int sR, int sC, int dir){
        int count = 0;

        while(!usedDir[sR][sC][dir]){

            usedDir[sR][sC][dir] = true;
            count++;

            sR = (sR + directions[dir][0] + h) % h;
            sC = (sC + directions[dir][1] + w) % w;

            char t = grid[sR].charAt(sC);
            if(t == 'R') dir = (dir+1) % 4;
            else if (t == 'L') dir = (dir+3) % 4;
        }
        return count;
    }
}