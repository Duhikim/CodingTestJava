package CodingTestStudy.Level2.VisitLength;

import java.lang.Math;

class Solution {
    // 좌표를 좌상단을 (0, 0), 우하단을 (10, 10)으로 재정의. (5,5)에서 시작
    // 길은 10*10짜리 boolean 매트릭스를 두개 만들어서 체크 (가로선 세로선)

    public int solution(String dirs) {
        int answer = 0;
        boolean[][] visitVer = new boolean[11][11];
        boolean[][] visitHor = new boolean[11][11];
        int currR = 5;
        int currC = 5;

        for(int i=0; i<dirs.length(); i++){
            char dir = dirs.charAt(i);
            int nextR= currR, nextC= currC;
            switch(dir){
                case 'U': nextR = currR-1; break;
                case 'D': nextR = currR+1; break;
                case 'R': nextC = currC+1; break;
                case 'L': nextC = currC-1; break;
                default: System.out.println("Unexpected input"); return -10;
            }
            if(nextR < 0 || nextC < 0 || nextR > 10 || nextC > 10) continue;

            boolean[][] visit;
            if(dir=='U' || dir=='D') visit = visitVer;
            else visit = visitHor;
            if(!visit[Math.min(nextR, currR)][Math.min(nextC, currC)]) answer++;
            visit[Math.min(nextR, currR)][Math.min(nextC, currC)] = true;

            currR = nextR;
            currC = nextC;
        }

        return answer;
    }
}