package CodingTestStudy.FurthestNodes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        Node[] nodes = new Node[n+1];
        for(int i=1; i<=n; i++){
            nodes[i] = new Node(i);
        }

        for(int[] e: edge){
            nodes[e[0]].connectTo.add(e[1]);
            nodes[e[1]].connectTo.add(e[0]);
        }

        int level = 0;
        Deque<Integer> currQue = new ArrayDeque<>();
        nodes[1].level = 0;
        currQue.add(1);
        while(!currQue.isEmpty()) {
            answer = currQue.size();
            level++;
            Deque<Integer> nextQue = new ArrayDeque<>();

            while(!currQue.isEmpty()) {
                int idx = currQue.pollFirst();
                for(int nextIdx: nodes[idx].connectTo){
                    if(nodes[nextIdx].level > level){
                        nodes[nextIdx].level = level;
                        nextQue.add(nextIdx);
                    }
                }
            }

            currQue = nextQue;
        }

        return answer;
    }

    class Node {
        int id;
        int level;
        List<Integer> connectTo;

        public Node(int id){
            this.id = id;
            this.level = Integer.MAX_VALUE;
            connectTo = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[][] vertex = new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        sol.solution(n, vertex);
    }
}