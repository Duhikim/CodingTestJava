package CodingTestStudy.Level2.DefenseGame;

public class Solution_ver1 {
	public int solution(int n, int k, int[] enemy) {
		int[] prevRoundSoldiers = new int[k+1];
		int[] currRoundSoldiers;

		int minimumRound = k-1; // 전부 무적권을 써서 넘어가도 k스테이지까지는 통과할 수 있음.

		prevRoundSoldiers[k] = n-enemy[0];
		prevRoundSoldiers[k-1] = n;

		int minimumKValue;
		int maximumKValue = k;

		for(int i=1; i<enemy.length; i++){
			minimumKValue = (k>i)? k-i : 0;
			currRoundSoldiers = new int[k+1];

			for(int kVal = minimumKValue; kVal <= maximumKValue; kVal++){
				currRoundSoldiers[kVal] = (kVal+1>k)? currRoundSoldiers[kVal] : Math.max(currRoundSoldiers[kVal], prevRoundSoldiers[kVal+1]);
				currRoundSoldiers[kVal] = (prevRoundSoldiers[kVal] < enemy[i])? currRoundSoldiers[kVal] : Math.max(currRoundSoldiers[kVal], prevRoundSoldiers[kVal] - enemy[i]);
				if(currRoundSoldiers[kVal] == 0) {
					maximumKValue = kVal-1;
					break;
				}
			}
			if(i > minimumRound && i+1 < enemy.length && currRoundSoldiers[0] < enemy[i+1]) {
				return i+1;
			}
			prevRoundSoldiers = currRoundSoldiers;
		}

		return enemy.length;
	}

	public static void main(String[] args) {
		Solution_ver1 sol = new Solution_ver1();
		int n = 7;
		int k = 3;
		int[] enemy = {4, 2, 4, 5, 3, 3, 1};

		System.out.println(sol.solution(n, k, enemy));

	}
}