package CodingTestStudy.FebruaryTest;

import java.io.*;
import java.util.*;
class Q4 {
	static int N, M, S, E, C;

	public class NodeManager{
		Node[] nodes;
		int[][] cost; // cost[a][b] : a에서 b로 가는 비용

		public NodeManager(){
			nodes = new Node[N+1];
			for(int i=1; i<=N; i++){
				nodes[i] = new Node(i);
			}
			cost = new int[N+1][N+1];
		}

		public void getLevels(){
			nodes[S].level = 0;
			PriorityQueue<int[]> workQue = new PriorityQueue<>((o1, o2)->o1[1] - o2[1]);
			workQue.add(new int[]{S, 0});

			while(!workQue.isEmpty()){
				int[] curr = workQue.poll();
				for(int nextIdx : nodes[curr[0]].connection){
					if(nodes[nextIdx].level > curr[1]+cost[curr[0]][nextIdx]){
						nodes[nextIdx].level = curr[1]+cost[curr[0]][nextIdx];
						workQue.add(new int[]{nextIdx, curr[1]+cost[curr[0]][nextIdx]});
					}
				}
			}
		}
	}

	public class Node{
		int number;
		int level;
		Set<Integer> connection;

		public Node(int num){
			this.number = num;
			this.level = Integer.MAX_VALUE;
			this.connection = new HashSet<>();
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] str = input.split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		input = br.readLine();
		str = input.split(" ");
		S = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		C = Integer.parseInt(str[2]);
		Q4 mainInstance = new Q4();
		Q4.NodeManager nm = mainInstance.new NodeManager();
		int checkpoint = 1;
		for(int i=0; i<M; i++){
			input = br.readLine();
			str = input.split(" ");
			int s = Integer.parseInt(str[0]);
			int e = Integer.parseInt(str[1]);
			int c = Integer.parseInt(str[2]);

			System.out.println("Check " + checkpoint);
			checkpoint++;
			nm.nodes[s].connection.add(e);
			nm.nodes[e].connection.add(s);
			nm.cost[s][e] = (C-1)/c + 1;
			nm.cost[e][s] = (C-1)/c + 1;
		}

		nm.getLevels();
		if(nm.nodes[E].level == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(nm.nodes[E].level);
	}
}