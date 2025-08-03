package CodingTestStudy.LightPathCycle;

import java.util.*;
import java.lang.Math;

class Solution {
    // 각 격자에서 어느방향으로 나갔는지 저장을 해둠
    // 격자를 돌다가 어느 격자에서 이미 나간 방향으로 가야하면 한 사이클 완료.
    // 이미 나간 방향은 다시 체크할 필요 없음
    // 방향은 상하좌우 네방향으로 나가는 4가지가 있음 (비트마스크로 체크) - 들어오는거는 체크 안해도 됨.
    // >> 1 1 1 1 순서로 각각 상 우 하 좌를 의미.
    // >> 현재 direction을 보며 S를 만나면 그대로, R을 만나면 >>1, L을 만나면 <<1을 하면 될듯.
    Node[][] nodes;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int h, w;

    class Node{
        char t;
        int usedDir;
        public Node(char t){
            this.t = t;
            usedDir = 0;
        }
    }

    public int[] solution(String[] grid) {
        h = grid.length;
        w = grid[0].length();
        nodes = new Node[h][w];

        for(int i=0; i<h; i++){for(int j=0; j<w; j++){
            nodes[i][j] = new Node(grid[i].charAt(j));
        }}

        List<Integer> answer = new ArrayList<>();

        for(int i=0; i<h; i++){for(int j=0; j<w; j++){
            for(int dir=1; dir<=8; dir<<=1){
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
        int nR, nC;

        while(true){
            if((nodes[sR][sC].usedDir & dir) != 0) return count;

            nodes[sR][sC].usedDir += dir;

            int d = 0;
            while((1<<d) != dir) d++;

            nR = sR + directions[d][0];
            nC = sC + directions[d][1];
            if(nR < 0) nR += h;
            else if(nR >= h) nR -= h;
            if(nC < 0) nC += w;
            else if(nC >= w) nC -= w;

            char t = nodes[nR][nC].t;
            if(t == 'R') dir = turnRight(dir);
            else if (t == 'L') dir = turnLeft(dir);

            count++;
            sR = nR;
            sC = nC;

        }
    }


    public int turnRight(int dir){
        return (dir==1)?8 : dir>>1;
    }
    public int turnLeft(int dir){
        return (dir==8)?1 : dir<<1;
    }
}