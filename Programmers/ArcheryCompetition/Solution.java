package CodingTestStudy.ArcheryCompetition;

class Solution {
	int myArrows;
	int[] apeach;
	int[] ryan;
	int[] answer;
	int maxGap = 0;
	int lowestScore;
	int arrowsOnLowestScore;

	public static void main(String[] args) {
		Solution sol = new Solution();
		int N = 9;
		int[] INFO = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
		for(int ans : sol.solution(N, INFO)){
			System.out.println(ans);
		}
	}

	public int[] solution(int n, int[] info) {
		this.myArrows = n;
		this.apeach = info;
		ryan = new int[11];
		answer = new int[11];
		lowestScore = 11;
		arrowsOnLowestScore = 0;
		DFS(0);
		if(maxGap == 0) return new int[]{-1};

		return answer;
	}

	public void DFS(int idx){
		if(idx == 11){
			int currGap = calculateScores();

			if(currGap >= maxGap){
				ryan[10] += myArrows;
				for(int i=0; i<11; i++){
					System.out.print(ryan[i]);
				}System.out.println("\n Max Gap = " + currGap);
				if(currGap > maxGap) {
					maxGap = currGap;
					lowestScore = 11;
					arrowsOnLowestScore = 0;
				}
				for(int i=10; i>=0; i--){
					if(ryan[i] != 0){
						if(10-i < lowestScore){
							lowestScore = 10-i;
							arrowsOnLowestScore = ryan[i];
							answer = ryan.clone();
						}
						else if(10-i == lowestScore){
							if(ryan[i] > arrowsOnLowestScore){
								arrowsOnLowestScore = ryan[i];
								answer = ryan.clone();
							}
						}
						break;
					}
				}
				ryan[10] -= myArrows;
			}
			return;
		}

		for(int i= idx; i<11; i++){
			ryan[i] += apeach[i]+1;
			myArrows -= apeach[i]+1;

			if(myArrows == 0){
				DFS(11);
			}
			else if(myArrows > 0){
				DFS(idx+1);
			}
			myArrows += apeach[i]+1;
			ryan[i] = 0;
			if(i==10) {
				DFS(11);
			}
		}
	}
	public int calculateScores(){
			int ryanSum=0, apeachSum=0;

			for(int i=0; i<11; i++){
				if (ryan[i] > apeach[i]) ryanSum += 10-i;
				else if(apeach[i] > 0) apeachSum += 10-i;
			}
			return ryanSum-apeachSum;
		}
}