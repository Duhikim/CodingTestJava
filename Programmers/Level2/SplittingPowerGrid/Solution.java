package CodingTestStudy.Level2.SplittingPowerGrid;

import java.util.*;
import java.lang.Math;

class Solution {
    Node[] nodes;
    class Node {
        int num;
        int value;
        List<Node> connectTo;
        public Node(int num){
            this.num = num;
            value = 1;
            connectTo = new ArrayList<>();
        }
    }

    public int solution(int n, int[][] wires) {
        nodes = new Node[n+1];
        for(int i=1; i<=n; i++){
            nodes[i] = new Node(i);
        }

        for(int[] wire: wires){
            nodes[wire[0]].connectTo.add(nodes[wire[1]]);
            nodes[wire[1]].connectTo.add(nodes[wire[0]]);
        }

        Queue<Node> pq = new PriorityQueue<>((a, b)->b.value - a.value);
        for(int i=1; i<=n; i++){
            if(nodes[i].connectTo.size() == 1) pq.add(nodes[i]);
        }

        while(true){
            Queue<Node> nextPq = new PriorityQueue<>((a, b)->b.value - a.value);

            Node maxNode = pq.poll();

            while(!pq.isEmpty()){
                Node node = pq.poll();
                Node nextNode = node.connectTo.get(0);

                if(nextNode.connectTo.size() == 1 && nextNode.connectTo.get(0) == node){
                    return Math.abs(nextNode.value - node.value);
                }

                nextNode.value += node.value;
                nextNode.connectTo.remove(node);
                if(nextNode.connectTo.size() == 1) nextPq.add(nextNode);
            }
            pq = nextPq;
            pq.add(maxNode);

            if(pq.isEmpty()) break;
        }
        // 여기에 도달할 수 없음
        int answer = -1;
        return answer;
    }
}