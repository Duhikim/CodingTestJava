package CodingTestStudy.Level2.Delivery;

import java.util.*;
import java.lang.Math;

class Solution {
    class Town {
        int id;
        int leastCost;
        List<int[]> connectTo;
        public Town(int id){
            this.id = id;
            this.leastCost = Integer.MAX_VALUE;
            this.connectTo = new ArrayList<>();
        }
        public void addPath(int id, int cost){
            this.connectTo.add(new int[]{id, cost});
        }
    }

    public int solution(int N, int[][] road, int K) {

        Town[] towns = new Town[N+1];
        Set<Integer> reachable = new HashSet<>();

        for(int i=1; i<=N; i++){
            towns[i] = new Town(i);
        }

        for(int[] edge: road){
            int town1 = edge[0], town2=edge[1], cost=edge[2];
            towns[town1].addPath(town2, cost);
            towns[town2].addPath(town1, cost);
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b)->a[1] - b[1]);
        pq.add(new int[]{1, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int idx = cur[0], cost = cur[1];

            if(towns[idx].leastCost <= cost) continue;
            towns[idx].leastCost = cost;
            if(cost <= K) reachable.add(idx);


            for(int i=0; i<towns[idx].connectTo.size(); i++){
                int[] next = towns[idx].connectTo.get(i);
                int nextIdx = next[0], nextCost = next[1] + cost;

//                 if(towns[nextIdx].leastCost <= nextCost) continue;
//                 towns[nextIdx].leastCost = nextCost;
//                 if(nextCost <= K) reachable.add(nextCost);

                pq.add(new int[]{nextIdx, nextCost});
            }

        }

        return reachable.size();
    }
}