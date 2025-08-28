package CodingTestStudy.Level3.MovingBallSimulation;

/*
x, y부터 시작해서 쿼리를 역순 역방향으로 적용.
가로쿼리, 세로쿼리 따로 적용하면 1차원으로 생각할 수 있음
경계에 도달할 때 그 다음쿼리만큼 분기 생김

*/

import java.util.*;

class Solution {
    boolean debug =
            // true;
            false;
    public long solution(int n, int m, int x, int y, int[][] queries) {

        List<Integer> vert = new ArrayList<>();
        List<Integer> hori = new ArrayList<>();

        for(int i=queries.length-1; i>=0; i--){
            switch(queries[i][0]){
                case 0:
                    hori.add(0-queries[i][1]);
                    break;
                case 1:
                    hori.add(queries[i][1]);
                    break;
                case 2:
                    vert.add(0-queries[i][1]);
                    break;
                case 3:
                    vert.add(queries[i][1]);
                    break;
            }
        }

        if(debug)System.out.println("행 기준 시작");
        long a = getStarts(vert, x, n);
        if(debug)System.out.println("행 기준 " + a + "가지 경우");
        if(debug)System.out.println("열 기준 시작");
        long b = getStarts(hori, y, m);
        if(debug)System.out.println("열 기준 " + b + "가지 경우");

        return a*b;
    }

    public int getStarts(List<Integer> arr, int finalTarget, int size){

        // 개념적으로 from에서 arr요소만큼 더해서 target에 도착하는 것
        // 하지만 실제로는 역순으로 계산해서 target에서 arr요소만큼 빼서 from에 도착함
        // Set로 하면 모든 수를 다 저장해야 하는데, 어차피 연속이므로 최소값과 최대값만 저장해놔도 될거같긴 함.

        int[] target = new int[2]; // 범위저장. [0]이 최소값 [1]이 최대값
        int[] from = new int[2];
        target[0] = target[1] = finalTarget;

        for(int move: arr){
            if(debug) System.out.println(move + "만큼 이동해야 함");
            if(debug) System.out.println("도착점 범위 : " + target[0] + " ~ " + target[1]);

            if(target[0]!=0) from[0] = Math.max(target[0] - move, 0);
            else from[0] = 0;
            if(target[1]!=size-1) from[1] = Math.min(target[1] - move, size-1);
            else from[1] = size-1
                    ;
            if(debug) System.out.println("출발점 범위 : " + from[0] + " ~ " + from[1]);

            // 결과 해석
            if(from[0] > from[1]) return 0;
            if(from[0]==0 && from[1] == size-1) return size;

            // 후처리
            target[0] = from[0];
            target[1] = from[1];

        }
        return target[1] - target[0] + 1;
    }
}