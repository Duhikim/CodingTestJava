package CodingTestStudy.Level3.Lighthouse;

import java.util.*;

public class Solution {

    class LightHouse{
        boolean lightOn;
        Set<Integer> connectTo;

        LightHouse(){
            lightOn = false;
            connectTo = new HashSet<>();
        }
    }

    public int solution(int n, int[][] lighthouse) {
        LightHouse[] lightHouses = new LightHouse[n+1];
        for(int i=1; i<=n; i++){
            lightHouses[i] = new LightHouse();
        }
        for(int[] bridge: lighthouse){
            lightHouses[bridge[0]].connectTo.add(bridge[1]);
            lightHouses[bridge[1]].connectTo.add(bridge[0]);
        }

        ///
        Deque<Integer> terminals = new ArrayDeque<>();
        for(int i=1; i<=n; i++){
            if(lightHouses[i].connectTo.size()==1) terminals.add(i);
        }

        while(!terminals.isEmpty()) {

            int idx = terminals.pollFirst();
            if(lightHouses[idx].connectTo.isEmpty()) continue;
            int nextIdx = lightHouses[idx].connectTo.iterator().next();

            if(!lightHouses[idx].lightOn) { // 현재 등대가 꺼져있는 경우
                lightHouses[nextIdx].lightOn = true;
            }
            lightHouses[nextIdx].connectTo.remove(idx);
            if(lightHouses[nextIdx].connectTo.size()==1)
                terminals.add(nextIdx);
        };
        ///

        int answer = 0;
        for(int i=1; i<=n; i++){
            if(lightHouses[i].lightOn) answer++;
        }
        System.out.println(answer);

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n=
//                2;
//                8;
                10;
//                13;
        int[][] lighthouse =
//                {{1,2}};
//        {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
//        {{1, 2}, {2, 3}, {2, 4}, {4, 7}, {5, 7}, {6, 7}, {4, 8}, {8, 9}, {8, 10}, {4, 11}, {11, 12}, {11, 13}};

        sol.solution(n, lighthouse);
    }
}