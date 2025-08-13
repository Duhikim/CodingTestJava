package CodingTestStudy.Level3.Network;

/*
<풀이>
연결되어 있는 컴퓨터들을 모두 없애고 더이상 없앨게 없는데 남아있는 컴퓨터가 있다면 1을 추가한다.
끝까지 반복.
 */


public class Solution {

	public int solution(int n, int[][] computers) {
		int answer = 0;

		boolean[] checked = new boolean[n];
		for(int i=0; i<n; i++){
			if(!checked[i]) {
				search(i, computers, checked);
				answer++;
			}
		}
		return answer;
	}

	public void search(int s, int[][] computers, boolean[] checked){
		checked[s] = true;
		for(int i=0; i<computers.length; i++){
			if(!checked[i] && computers[s][i]==1){
				search(i, computers, checked);
			}
		}
	}
}