package CodingTestStudy.ReturnSoldiers;

import java.util.*;

public class Solution {
	class NodeManager{
		Node[] nodes;

		public NodeManager(int n, int[][] roads, int destination){

			nodes = new Node[n+1];

			for(int i=1; i<=n; i++){
				nodes[i] = new Node(i);
			}

			for(int[] road: roads){
				nodes[road[0]].connection.add(nodes[road[1]]);
				nodes[road[1]].connection.add(nodes[road[0]]);
			}

			setLevels(destination);
		}

		public void setLevels(int idx){
			Queue<int[]> workQue = new ArrayDeque<>();
			nodes[idx].level = 0;
			workQue.add(new int[]{idx, 0});

			while(!workQue.isEmpty()){
				int[] curr = workQue.poll();
				for(Node nextNode : nodes[curr[0]].connection){
					if(nextNode.level == -1 || nextNode.level > curr[1]+1){
						nextNode.level = curr[1]+1;
						workQue.add(new int[]{nextNode.number, curr[1]+1});
					}
				}
			}

		}
	}

	class Node {
		int number;
		int level;
		Set<Node> connection;

		public Node(int num){
			this.number = num;
			this.level = -1;
			connection = new HashSet<>();
		}
	}

	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		int[] answer = new int[sources.length];
		NodeManager nm = new NodeManager(n, roads, destination);

		for(int i=0; i<sources.length; i++){
			answer[i] = nm.nodes[sources[i]].level;
		}

		return answer;
	}
}