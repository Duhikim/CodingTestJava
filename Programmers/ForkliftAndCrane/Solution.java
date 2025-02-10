package CodingTestStudy.ForkliftAndCrane;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	int answer;
	public int solution(String[] storage, String[] requests) {
		// Accessible deque를 만들어서 지게차 운전할 때 데크를 한번 순회하며 모두 없애고 새로운 데크 생성.
		// 크레인 운전할 때는 전체순회할건데, 만약 꺼내는 노드가 Accessible deque에 있으면 위 작업 수행.
		answer = 0;
		int n = storage.length;
		int m = storage[0].length();

		char[][] containers = new char[n][m];
		boolean[][] accessible = new boolean[n][m];
		List<String> accessibleContainers = new ArrayList<>();

		stackContainers(containers, storage, n, m);
		makingTubes(accessible, accessibleContainers, n, m);

		for(String request: requests){
			if(requests.length==1){
				driveForkLift(request.charAt(0), containers, accessible, accessibleContainers);
			}
			else{
				driveCrane(request.charAt(0), containers, accessible, accessibleContainers);
			}
		}

		return answer;
	}

	public void driveForkLift(char T, char[][] containers, boolean[][] accessible, List<String> accessibleContainers){
		for(int i=0; i<accessibleContainers.size(); i++){
			String[] str = accessibleContainers.get(i).split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			if(containers[r][c] == T){

			}
		}
	}

	public void driveCrane(char T, char[][] containers, boolean[][] accessible, List<String> accessibleContainers){

	}

	public void stackContainers(char[][] containers, String[] storage, int n, int m){
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				containers[i][j] = storage[i].charAt(j);
			}
		}
	}

	public void makingTubes(boolean[][] accessible, List<String> accessibleContainers, int n, int m){
		for(int j=0; j<m; j++){
			accessible[0][j] = true;
			accessible[n-1][j] = true;
			accessibleContainers.add(0+" "+j);
			accessibleContainers.add(n-1+" "+j);
		}
		for(int i=1; i<n-1; i++){
			accessible[i][0] = true;
			accessible[i][m-1] = true;
			accessibleContainers.add(i+" "+0);
			accessibleContainers.add(i+" "+(m-1));
		}
	}
}