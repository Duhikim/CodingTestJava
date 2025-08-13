package CodingTestStudy.Level2.ArcheryCompetition;

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


//package CodingTestStudy.Level2.ArcheryCompetition;
//// 어떤 점수에서 지거나 비길거면 한발도 안맞춘다.
//// 이기려면 어피치보다 딱 한개 많이 쏴야 한다.
//// 이기기 위한 화살 한방당 가중치를 계산해서 가중치가 높은 것을 취할것.
//// 하지만 이 경우, 반례가 있을 수 있음.
//// 	예를들어 어피치가 10점에 9발, 9점에 8발을 넣었다고 하면 각 포인트에서 이기기 위해서는 10점에 10발, 9점에 9발을 넣어야 한다.
//// 	이 경우 가중치는 20/10, 18/9로 2로 동일하다.
//// 	하지만, 10점에 10발을 넣어서 10점을 얻는 경우와, 9점에 9발을 넣어서 9점을 얻고 남은 한발로 추가 점수를 얻을 수 있는지 여부는 생각해봐야 한다.
////	추가 점수를 얻을 수 없다면 10점에 10발을 넣고 10점을 얻는것이 1점 이득이다.
////	반대로 다른곳에서 추가 한 발로 n점을 얻을 수 있다면, 9점에 9발을 넣고 나머지 한 발로 n점을 얻는것이 n-1점 이득이다.
////		남은 한발로 1점을 얻을 수 있는 포인트가 있다면, 그것은 score가 1인 포인트로 확정된다. 이 경우 점수 자체는 10점으로 똑같음. 점수가 같으면 낮은 score 즉 9, 3을 취해야 한다.
////		남은 한 발로 2점을 얻을 수 있는 포인트는 score 2 포인트로 확정된다. 이 경우 가중치는 2로 10, 9와 동일하다.
////		남은 한 발로 3점 이상을 얻는다면, 그것은 가중치가 3 이상이므로 이미 처리되었어야 한다. ( 이미 처리가 되어서 10이나 9를 못취하고 더 낮은 점수를 취해야 하는 상황이 오면? 생각해 볼 필요가 있음)
////		즉, 가중치가 같은 경우, 두 경우의 minReq의 차이로
//
//import java.sql.Array;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class Solution {
//	public class PointsManager {
//		ArrayList<Points> pointsArray;
//		int[] apeach;
//		int[] arrows;
//		int[] finalAnswer;
//		int myArrows;
//		int maxGap;
//		int lowestScore; // 맞춘 가장 낮은 번호
//		int arrowOnLS; // 가장 낮은 번호 맞은 화살 개수
//		int[] savedAnswer;
//		int savedIdx;
//
//		public PointsManager(int n, int[] info) {
//			this.pointsArray = new ArrayList<>();
//			for(int i=0; i<11; i++){
//				Points point = new Points(10-i, info[i]);
//				pointsArray.add(point);
//			}
//			Collections.sort(pointsArray);
//			apeach = info.clone();
//			arrows = new int[11];
//			finalAnswer = new int[11];
//			myArrows = n;
//			maxGap = 0;
//			lowestScore = 0;
//			arrowOnLS = 0;
//		}
//
//		public void addPoint(Points point){
//			arrows[10-(int)point.score] = (int)point.minReq;
//			myArrows -= (int)point.minReq;
//		}
//		public void removePoint(Points point){
//			arrows[10-(int)point.score] = 0;
//			myArrows += (int)point.minReq;
//		}
//
//		public void search(int idx){
//			if(idx==11){
//				arrows[10] += myArrows;
//				int currGap = (int)calculateScores();
//				if(currGap >= maxGap){
//					maxGap = currGap;
//
//				}
//			}
//			if(myArrows > 0){
//				addPoint(pointsArray.get(idx));
//				search(idx+1);
//			}
//			else if(myArrows == 0){
//
//			}
//			else{
//
//			}
//		}
//		public double calculateScores(){
//			double ryanSum=0, apeachSum=0;
//
//			for(int i=0; i<11; i++){
//				if (arrows[i] > apeach[i]) ryanSum += 10-i;
//				else if(apeach[i] > 0) apeachSum += 10-i;
//			}
//			return ryanSum-apeachSum;
//		}
//	}
//
//	public class Points implements Comparable<Points> {
//		double score; // 점수
//		double minReq; // 이기기 위한 최소의 화살 개수
//		double weight; // 예상 가중치 (화살 한개당 가치) -> 상대가 한 개 이상 맞췄는데 이기면 상대가 먹을 점수를 내가 먹으므로 score*2/minReq 으로 계산. 한개도 못맞춘 경우 어차피 버리는 점수를 취하는것이므로 score/minReq(1) 이 된다.
//		boolean win;
//
//		public Points(int score, int apeach){
//			this.score = score;
//			this.minReq = apeach+1;
//			this.weight = (apeach==0)? score: (score * 2) / this.minReq;
//			this.win = false;
//		}
//		public boolean canGetThisPoint(int myArrow){
//			return myArrow >= minReq;
//		}
//		public int winThisPoint(){
//			this.win = true;
//			return (int)minReq; // 사용 화살 수
//		}
//		@Override
//		public int compareTo(Points o){
//			if(this.weight != o.weight) {
//				return (o.weight > this.weight)? 1 : -1;
//			}
//			return (this.score > o.score)? 1 : -1; // score가 같은 point는 없다.
//		}
//		public String toString(){
//			return "[Score : " + score + " , Minimum Requirement : " + minReq + " , weight : " + weight + "]\n";
//		}
//	}
//	public int[] solution(int n, int[] info) {
//
//		ArrayList<Points> points = new ArrayList<>();
//
//		for(int i=0; i<11; i++){
//			Points point = new Points(10-i, info[i]);
//			points.add(point);
//		}
//		Collections.sort(points);
//
//
//		int[] answer = new int[11];
//		int ryan = 0;
//
//
//
//		int apeachScore = 0, ryanScore = 0;
//		for(int i=0; i<11; i++){
//			if(answer[i] > info[i]) ryanScore+= (10-i);
//			else if(info[i] > 0) apeachScore += (10-i);
//		}
//		if(apeachScore>= ryanScore) return new int[]{-1};
//
//		return answer;
//	}
//	public void DFS (int idx, int myArrows, int[] ryan, int[] apeach, ArrayList<Points> points){
//		if(idx==10 || myArrows == 0){
//			ryan[10] += myArrows;
//
//		}
//
//
//	}
//	public double calculateScores(int[] ryan, int[] apeach){
//		double ryanSum=0, apeachSum=0;
//
//		for(int i=0; i<11; i++){
//			if (ryan[i] > apeach[i]) ryanSum += 10-i;
//			else if(apeach[i] > 0) apeachSum += 10-i;
//		}
//		return ryanSum-apeachSum;
//	}
//	public int[] whatArrayShouldIPick(int[] one, int[] two){
//		for(int i=10; i>=0; i--){
//			if(one[i] == two[i]) continue;
//			return (one[i]>two[i])? one: two;
//		}
//		return one;
//	}
//
//	public static void main(String[] args) {
//		Solution sol = new Solution();
//		int n = 5;
//		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
//		sol.solution(n, info);
//	}
//}