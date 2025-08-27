package CodingTestStudy.Level3.HikingCourse;
import java.lang.Math;
import java.util.*;

class Solution {
    Set<Integer> gateSet;
    Set<Integer> summitSet;
    int[] answer;
    int n;
    List<Map<Integer, Integer>> costAtoB;
    int[] intensity;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        costAtoB = new ArrayList<>();
        intensity = new int[n+1];
        gateSet = new HashSet<>();
        summitSet = new HashSet<>();

        for(int gate: gates) gateSet.add(gate);
        for(int summit: summits) summitSet.add(summit);

        for(int i=0; i<=n; i++){
            costAtoB.add(new HashMap<>());
        }

        for(int[] path: paths){
            costAtoB.get(path[0]).put(path[1], path[2]);
            costAtoB.get(path[1]).put(path[0], path[2]);
        }

        search();

        return answer;
    }

    public void search(){

        Arrays.fill(intensity, Integer.MAX_VALUE);
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for(int gate: gateSet){
            intensity[gate] = 0;
            pq.add(new int[]{gate, 0});
        }

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int start = curr[0];

            if(intensity[start] != curr[1]) continue;
            Map<Integer, Integer> map = costAtoB.get(start);
            // System.out.println(start + "번 노드 출발");

            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                int target = entry.getKey();
                int cost = entry.getValue();

                if(gateSet.contains(target)) continue;
                int nIntensity = Math.max(intensity[start], cost);
                if(nIntensity >= intensity[target]) continue;
                intensity[target] = nIntensity;
                // System.out.println(target + "번 노드값 갱신 : " + nIntensity);
                if(intensity[target] > answer[1]) continue;
                if(summitSet.contains(target)){
                    if(intensity[target] < answer[1] || target < answer[0]){
                        // System.out.println("answer 갱신 >> [" + target + " , " + nIntensity + "]");
                        answer[0] = target;
                        answer[1] = nIntensity;
                    }
                } else{
                    pq.add(new int[]{target, nIntensity});
                }
            }
        }


    }
}