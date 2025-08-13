package CodingTestStudy.Level2.SplittingPowerGrid;

import java.util.*;
import java.lang.Math;

class Solution2 {
    List<Integer>[] connect;
    boolean[] visit;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        connect = new List[n+1];
        visit = new boolean[n+1];
        for(int i=1; i<=n; i++) connect[i] = new ArrayList<>();
        for(int[] wire: wires){
            connect[wire[0]].add(wire[1]);
            connect[wire[1]].add(wire[0]);
        }

        for(int[] wire: wires){
            visit[wire[0]] = true;
            visit[wire[1]] = true;
            int count = dfs(wire[0]);
            visit[wire[0]] = false;
            visit[wire[1]] = false;
            answer = Math.min(answer, Math.abs(n-2*count));
        }

        return answer;
    }
    public int dfs(int start){
        int count = 1;

        for(int idx: connect[start]){
            if(visit[idx]) continue;
            visit[idx] = true;
            count += dfs(idx);
            visit[idx] = false;
        }

        return count;
    }
}