package CodingTestStudy.Level3.OddEvenTree;

import java.util.*;

public class Solution {

    Map<Integer, Node> nodeMap;

    class Node{
        int value;
        List<Integer> connectTo;
        int legs;
        boolean visit;

        public Node(int value){
            this.value = value;
            this.connectTo = new ArrayList<>();
            this.legs = 0;
            this.visit = false;
        }

        public void connect(int idx){
            connectTo.add(idx);
            legs++;
        }

        public void makeAllNoVisit(){
            this.visit = false;
            Deque<Integer> workQue = new ArrayDeque<>();
            for(int idx: connectTo){
                if(nodeMap.get(idx).visit) workQue.add(idx);
            }
            while(!workQue.isEmpty()){
                int idx = workQue.pollFirst();
                nodeMap.get(idx).visit = false;
                for(int nextIdx: nodeMap.get(idx).connectTo){
                    if(nodeMap.get(nextIdx).visit) workQue.add(nextIdx);
                }
            }
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        nodeMap = new HashMap<>();

        for(int idx: nodes){
            nodeMap.put(idx, new Node(idx));
        }
        for(int[] edge: edges){
            nodeMap.get(edge[0]).connect(edge[1]);
            nodeMap.get(edge[1]).connect(edge[0]);
        }

        int[] answer = new int[]{0, 0}; // 홀짝 트리, 역홀짝 트리

        for(int idx: nodes){
            if(isOddEvenTree(idx, 1)) answer[0]++;
            else if(isOddEvenTree(idx, 0)) answer[1]++;
        }

        return answer;
    }

    public boolean isOddEvenTree(int num, int zeroForReverse){
        if(((num - nodeMap.get(num).legs) & 1) == zeroForReverse) return false;

        nodeMap.get(num).visit = true;
        Deque<Integer> workQue = new ArrayDeque<>();
        for(int idx: nodeMap.get(num).connectTo){
            if(((idx - (nodeMap.get(idx).legs-1))&1)==zeroForReverse){
                nodeMap.get(num).makeAllNoVisit();
                return false;
            }
            workQue.add(idx);
        }
        while(!workQue.isEmpty()){
            int idx = workQue.pollFirst();
            if(((idx - (nodeMap.get(idx).legs-1))&1)==zeroForReverse){
                nodeMap.get(num).makeAllNoVisit();
                return false;
            }

            nodeMap.get(idx).visit = true;
            for(int nextIdx: nodeMap.get(idx).connectTo){
                if(!nodeMap.get(nextIdx).visit) workQue.add(nextIdx);
            }
        }

        nodeMap.get(num).makeAllNoVisit();
        return true;
    }

}
