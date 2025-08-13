package CodingTestStudy.Level3.ArchaeologicalDiscovery;

import java.lang.Math;

class Solution {
    // 맨 윗줄은 최대 8개로, 각 칸을 0번, 1번, 2번, 3번 돌리는지에 따라 4^8 = 약 6.5만 가지 경우의 수가 발생함.
    // 그 밑줄은 윗줄에 종속됨.
    int w;
    int[][] clockHands;
    int answer;

    public int solution(int[][] clockHands) {
        this.w = clockHands.length;
        this.clockHands = clockHands;
        answer= Integer.MAX_VALUE;
        dfs(0, 0);
        return answer;
    }

    public void dfs(int col, int count){
        if(col == w){
            // 여기서 하단 모두 조작
            // 깊은 복사
            int[][] copy = new int[w][w];
            for(int i=0; i<w; i++)for(int j=0; j<w; j++){
                copy[i][j] = clockHands[i][j];
            }

            for(int i = 1; i<w; i++){
                for(int j=0; j<w; j++){
                    int rqdRotation = (4 - copy[i-1][j]) % 4;
                    copy[i-1][j] = (copy[i-1][j] + rqdRotation) % 4; // 상
                    copy[i][j] = (copy[i][j] + rqdRotation) % 4; // 본인
                    if(j-1 >= 0) copy[i][j-1] =(copy[i][j-1] + rqdRotation) % 4; // 좌
                    if(j+1 < w) copy[i][j+1] =(copy[i][j+1] + rqdRotation) % 4; // 우
                    if(i+1 < w) copy[i+1][j] =(copy[i+1][j] + rqdRotation) % 4; // 하
                    count += rqdRotation;
                }
            }
            for(int i=0; i<w; i++)for(int j=0; j<w; j++){
                if(copy[i][j] != 0) return;
            }

            answer = Math.min(answer, count);
            return;
        }

        for(int i=0; i<4; i++){
            clockHands[0][col] = (clockHands[0][col]+i)%4;
            if(col-1>=0) clockHands[0][col-1] = (clockHands[0][col-1]+i)%4;
            if(col+1<w) clockHands[0][col+1] = (clockHands[0][col+1]+i)%4;
            clockHands[1][col] = (clockHands[1][col]+i)%4;

            dfs(col+1, count+i);

            clockHands[1][col] = (clockHands[1][col]-i+4)%4;
            if(col+1<w) clockHands[0][col+1] = (clockHands[0][col+1]-i+4)%4;
            if(col-1>=0) clockHands[0][col-1] = (clockHands[0][col-1]-i+4)%4;
            clockHands[0][col] = (clockHands[0][col]-i+4)%4;
        }

    }
}