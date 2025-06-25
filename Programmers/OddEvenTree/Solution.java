package CodingTestStudy.OddEvenTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        // 노드에서 제일 큰 숫자+1 크기의 트리 생성.
        //

        Arrays.sort(nodes);
        int biggestValue = nodes[nodes.length - 1];
        Node.tree = new Node[biggestValue + 1];

        for(int i=0; i<nodes.length; i++) {
            Node.tree[nodes[i]] = new Node(nodes[i]);
        }
        for(int i=0; i<edges.length; i++) {
            Node.tree[edges[i][0]].connections.add(Node.tree[edges[i][1]]);
            Node.tree[edges[i][1]].connections.add(Node.tree[edges[i][0]]);
        }


    }

}

class Node {

    static Node[] tree;

    int value;
    List<Node> connections;

    Node(int value) {
        this.value = value;
        connections = new ArrayList<Node>();
    }

}