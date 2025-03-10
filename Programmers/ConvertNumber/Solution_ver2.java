package CodingTestStudy.ConvertNumber;

public class Solution_ver2 {
	public int solution(int x, int y, int n) {
		if(x==y) return 0;
		int[] answer = new int[y+1];
		for(int i=0; i<=y; i++){
			answer[i] = Integer.MAX_VALUE;
		}

		answer[x] = 0;

		for(int i=x; i<=y; i++){
			if(answer[i]== Integer.MAX_VALUE) continue;
			if(i*2 <= y) answer[i*2] = Math.min(answer[i*2], answer[i] + 1);
			if(i*3 <= y) answer[i*3] = Math.min(answer[i*3], answer[i] + 1);
			if(i+n <= y) answer[i+n] = Math.min(answer[i+n], answer[i] + 1);
		}

		return (answer[y] == Integer.MAX_VALUE)? -1 : answer[y];
	}



	public static void main(String[] args) {

		Solution_ver2 sol = new Solution_ver2();
		sol.solution(10, 40, 5);
	}
}