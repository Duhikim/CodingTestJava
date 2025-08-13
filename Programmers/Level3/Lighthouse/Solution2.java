package CodingTestStudy.Level3.Lighthouse;

import java.util.ArrayList;

public class Solution2 {

    boolean[] visit;
    ArrayList<Integer>[] connect;

    public int solution(int n, int[][] lighthouse) {
        visit = new boolean[n+1];
        connect = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            connect[i] = new ArrayList<>();
        }

        for(int[] con : lighthouse){
            connect[con[0]].add(con[1]);
            connect[con[1]].add(con[0]);
        }

        int[] answer = search(1);
        return Math.min(answer[0], answer[1]);
    }

    public int[] search(int idx){
        int[] result = new int[]{0, 1}; // off, on
        visit[idx] = true;

        for(int nextIdx: connect[idx]){
            if(!visit[nextIdx]){
                int[] nextResult = search(nextIdx);
                result[0] += nextResult[1];
                result[1] += Math.min(nextResult[0], nextResult[1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        int n=
//                2;
                8;
//                10;
//                13;
        int[][] lighthouse =
//                {{1,2}};
        {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
//        {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
//        {{1, 2}, {2, 3}, {2, 4}, {4, 7}, {5, 7}, {6, 7}, {4, 8}, {8, 9}, {8, 10}, {4, 11}, {11, 12}, {11, 13}};

        System.out.println(sol.solution(n, lighthouse));
    }
}