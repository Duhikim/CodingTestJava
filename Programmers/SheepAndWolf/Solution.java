package CodingTestStudy.SheepAndWolf;


import java.util.ArrayList;
import java.util.List;

public class Solution {
    Node[] nodes;
    int answer;
    int sheep;
    int wolves;
    int totalSheep = 0;
    boolean findAnswer = false;

    class Node{
        int id;
        int kind; // 0 for sheep, 1 for wolf
        List<Node> sons;
        Node parent;

        public Node(int id, int kind){
            this.id = id;
            this.kind = kind;
            sons = new ArrayList<>();
        }

    }

    public int solution(int[] info, int[][] edges) {
        nodes = new Node[info.length];
        for(int i=0; i<info.length; i++){
            nodes[i] = new Node(i, info[i]);
            if(info[i]==0) totalSheep++;
        }
        for(int[] edge: edges){
            int parent = edge[0];
            int child = edge[1];
            nodes[parent].sons.add(nodes[child]);
            nodes[child].parent = nodes[parent];
        }

        // 늑대 말단부는 어차피 안가므로 미리 다 잘라서 연산을 줄임
        for(Node node: nodes){
            Node curr = node;
            while(curr.kind==1 && curr.sons.isEmpty()){
                node.parent.sons.remove(node);
                curr = curr.parent;
            }
        }

        sheep = 1; wolves = 0;
        search(nodes[0], nodes[0].sons);

        return answer;
    }

    public void search(Node curr, List<Node> nextNodes){

        if(nextNodes.isEmpty() || sheep <= wolves){
            System.out.println("더 이상 진행 불가");
            if(nextNodes.isEmpty()) System.out.println("트리 끝까지 내려갔음.");
            else System.out.println("양의 수 " + sheep + "마리, 늑대 수 " + wolves + " 마리");
            if(sheep > answer) {
                answer = sheep;
                System.out.println("정답 갱신. 양 " + answer + " 마리");
            }
            if(answer==totalSheep){
                System.out.println("최대값 " + answer + " 마리 도달");
                findAnswer = true;
            }
            System.out.println(curr.id + " 노드 탈출.");
            return;
        }

        for(int i=0; i<nextNodes.size(); i++){

            Node nextNode = nextNodes.get(i);
            nextNodes.addAll(nextNode.sons);
            nextNodes.remove(i);
            if(nextNode.kind==0) sheep++;
            if(nextNode.kind==1) wolves++;
            System.out.println(nextNode.id + "노드 진입, 직전 노드 : " + curr.id);

            search(nextNode, nextNodes);
            if(findAnswer) return;

            if(nextNode.kind==0) sheep--;
            if(nextNode.kind==1) wolves--;
            nextNodes.add(i, nextNode);
            nextNodes.removeAll(nextNode.sons);
        }

    }

}