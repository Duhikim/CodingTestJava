package CodingTestStudy.ReturnSoldiers;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	class NodeManager{
		Node[] nodes;

		public NodeManager(int n, int[][] roads, int destination){

			nodes = new Node[n+1];

			for(int i=1; i<=n; i++){
				nodes[i] = new Node(i);
			}

			for(int[] road: roads){
				nodes[road[0]].connection.add(road[1]);
				nodes[road[1]].connection.add(road[0]);
			}

			setLevels(destination, 0);
		}

		public void setLevels(int idx, int lv){
			nodes[idx].level = lv++;
			/*
			for(Integer newIdx : nodes[idx].connection){ // DFS가 되는데 시간이 오래걸릴거같음
				 if(nodes[newIdx].level == -1 || nodes[newIdx].level > lv+1) setLevels(newIdx, lv+1);
			}
			*/
			Set<Integer> currLevel = nodes[idx].connection;
			Set<Integer> nextLevel;

			while (!currLevel.isEmpty()) {
				nextLevel = new HashSet<>();

				for (Integer newIdx : currLevel) {
					if (nodes[newIdx].level == -1 || nodes[newIdx].level > lv) {
						nodes[newIdx].level = lv;
						nextLevel.addAll(nodes[newIdx].connection);
					}
				}
				for (Integer newIdx : currLevel) {
					nextLevel.remove(newIdx);
				}

				lv++;
				currLevel = nextLevel;
			}
		}
	}

	class Node {
		int number;
		int level;
		Set<Integer> connection;

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