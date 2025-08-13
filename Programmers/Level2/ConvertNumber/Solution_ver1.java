package CodingTestStudy.Level2.ConvertNumber;

public class Solution_ver1 {
	public int solution(int x, int y, int n) {
		int[] answer = {Integer.MAX_VALUE};
		DFS(x, y, n, answer, 0);
		return (answer[0] == Integer.MAX_VALUE)? -1 : answer[0];
	}

	public void DFS(int x, int y, int n, int[] answer, int count){
		if(x > y || count >= answer[0]) return;
		if(x==y) {
			answer[0] = count;
			return;
		}

		for(int i=0; i<3; i++){
			switch(i) {
				case 0: DFS(x+n, y, n, answer, count+1); break;
				case 1: DFS(x*2, y, n, answer, count+1); break;
				case 2: DFS(x*3, y, n, answer, count+1); break;
			}
		}
	}

	public static void main(String[] args) {

		Solution_ver1 sol = new Solution_ver1();
		sol.solution(10, 40, 5);
	}
}