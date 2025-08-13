package CodingTestStudy.Level2.QuadCompression;

public class Solution {

	public int[] solution(int[][] arr) {
		int[] answer = {0, 0};

		quadComp(arr, 0, 0, arr.length, answer);

		return answer;
	}

	public void quadComp(int[][] arr, int sX, int sY, int size, int[] answer) {
		// compFin 함수의 결과가 false면 배열을 분할하는 함수.

		if(compFin(arr, sX, sY, size, answer)) return;
		quadComp(arr, sX, sY, size/2, answer);
		quadComp(arr, sX+size/2, sY, size/2, answer);
		quadComp(arr, sX, sY+size/2, size/2, answer);
		quadComp(arr, sX+size/2, sY+size/2, size/2, answer);
	}

	public boolean compFin(int[][] arr, int sX, int sY, int size, int[] answer) {
		// 배열 내의 숫자가 모두 똑같은지 확인하는 함수.
		//같으면 true, 하나라도 다르면 false를 반환한다.

		int oneOrZero = arr[sX][sY]; // 첫 항의 숫자 입력. 0 혹은 1
		for (int i = sX; i < sX+size; i++) {
			for (int j = sY; j < sY+size; j++) {
				if (arr[i][j] != oneOrZero) {
					return false; // 하나라도 다르면 false를 반환한다.
				}
			}
		}
		answer[oneOrZero]++; // 모두 같으면 answer의 해당되는 값을 하나 올려주고 true를 반환한다.
		return true;
	}
}