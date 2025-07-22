package CodingTestStudy.OddEvenTree;

import java.util.*;

public class Solution2 {

    Map<Integer, Node> nodeMap;

    class Node{
        int value;
        List<Integer> connectTo;
        int legs;
        boolean oddEvenWhenRoot;
        boolean visit;

        public Node(int value){
            this.value = value;
            this.connectTo = new ArrayList<>();
            this.legs = 0;
            this.oddEvenWhenRoot = false;
            this.visit = false;
        }

        public void connect(int idx){
            connectTo.add(idx);
            legs++;
        }

    }

    public int[] solution(int[] nodes, int[][] edges) {
        nodeMap = new HashMap<>();

        for(int idx: nodes){ // 노드 생성
            nodeMap.put(idx, new Node(idx));
        }
        for(int[] edge: edges){ // 노드 연결
            nodeMap.get(edge[0]).connect(edge[1]);
            nodeMap.get(edge[1]).connect(edge[0]);
        }

        List<List<Integer>> treeList = new ArrayList<>();
        for(int idx: nodes){ // 트리로 분할
            Node node = nodeMap.get(idx);
            if(node.visit)continue;
            List<Integer> tree = new ArrayList<>();
            node.visit = true;
            tree.add(node.value);
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addAll(node.connectTo);
            while(!deque.isEmpty()){
                Node nextNode = nodeMap.get(deque.poll());
                if(nextNode.visit) continue;
                nextNode.visit = true;
                tree.add(nextNode.value);
                deque.addAll(nextNode.connectTo);
            }
            treeList.add(tree);
        }

        for(int idx: nodes){ // 다리 갯수가 홀수인지 짝수인지, value가 홀수인지 짝수인지
            Node node = nodeMap.get(idx);
            if(node.legs == 0) { // 독립 노드인 경우
                node.oddEvenWhenRoot = ((idx & 1) == 0);
            }
            else if( ((idx - node.legs)&1) == 1 ){ // 독립 노드가 아닌 경우
                node.oddEvenWhenRoot = false;
            }
            else{
                node.oddEvenWhenRoot = true;
            }
        }

        int[] answer = new int[]{0, 0}; // 홀짝 트리, 역홀짝 트리

        /// 트리 하나씩 볼건데, 트리에 루트시 홀짝인 항이 한개면 역홀짝트리++, 역홀짝인 항이 한개면 홀짝트리++
        for(List<Integer> tree: treeList){
            int oddEven = 0, rOddEven = 0;
            for(int idx: tree){
                Node node = nodeMap.get(idx);
                if(node.oddEvenWhenRoot) oddEven++;
                else rOddEven++;
            }
            if(oddEven==1) answer[0]++;
            if(rOddEven==1) answer[1]++;
        }

        ///

        return answer;
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        int[] nodes;
        int[][] edges;
        int[] result;
        int[] calculate;

        nodes = new int[]{11, 9, 3, 2, 4, 6};
        edges = new int[][]	{{9, 11}, {2, 3}, {6, 3}, {3, 4}};
        result = new int[] {1, 0};

        calculate = sol.solution(nodes, edges);
        if(calculate[0]==result[0] && calculate[1]==result[1]) System.out.println("true");
    }
}
