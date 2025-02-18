package CodingTestStudy.ForkliftAndCrane;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	public class ContainerManager{
		Container[][] containers;
		int remain;
		int row, col;
		Queue<int[]> workQue;

		public ContainerManager(String[] storage){
			this.row = storage.length;
			this.col = storage[0].length();
			this.containers = new Container[row][col];
			this.remain = row*col;
			this.workQue = new ArrayDeque<>();

			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					boolean accessible = false;
					if(i==0 || i==row-1 || j==0 || j==col-1) accessible = true;
					containers[i][j] = new Container(storage[i].charAt(j), accessible);
				}
			}
		}
		public void removeWithForklift(char c){

			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					if (containers[i][j].value == c && containers[i][j].accessible && !containers[i][j].removed) {
						workQue.add(new int[]{i, j});
					}
				}
			}
			while(!workQue.isEmpty()) {
				int R = workQue.peek()[0];
				int C = workQue.poll()[1];
				containers[R][C].removed = true;
				remain--;
				makeNearAccessible(R, C);
			}
		}
		public void makeNearAccessible(int R, int C){
			int[] newR = {R+1, R-1, R, R};
			int[] newC = {C, C, C+1, C-1};
			for(int i=0; i<4; i++){
				if(newR[i] < 0 || newR[i] >= row || newC[i] < 0 || newC[i] >= col) continue;
				if(containers[newR[i]][newC[i]].accessible) continue;
				if(containers[newR[i]][newC[i]].removed && !containers[newR[i]][newC[i]].visited) {
					containers[R][C].visited = true;
					makeNearAccessible(newR[i], newC[i]);
					containers[R][C].visited = false;
				}
				containers[newR[i]][newC[i]].accessible = true;
			}
		}

		public void removeWithCrane(char c){
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					if (containers[i][j].value == c && !containers[i][j].removed) {
						workQue.add(new int[]{i, j});
					}
				}
			}
			while(!workQue.isEmpty()) {
				int R = workQue.peek()[0];
				int C = workQue.poll()[1];
				containers[R][C].removed = true;
				remain--;
				if(containers[R][C].accessible) makeNearAccessible(R, C);
			}
		}
	}

	public class Container{
		char value;
		boolean accessible;
		boolean removed;
		boolean visited; // 재귀시 이미 방문한 노드인지 체크

		public Container(char v, boolean acc){
			this.value = v;
			this.accessible = acc;
			this.removed = false;
			this.visited = false;
		}
	}

	public int solution(String[] storage, String[] requests) {
		ContainerManager cm = new ContainerManager(storage);

		for(String cmd: requests){
			if(cmd.length()==1){
				cm.removeWithForklift(cmd.charAt(0));
			}
			else{
				cm.removeWithCrane(cmd.charAt(0));
			}
		}

		return cm.remain;
	}
}