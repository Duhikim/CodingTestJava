package CodingTestStudy.IntegerTriangle;

class Solution {
	/*
	<풀이>
	아래서부터 세면 최적의 길을 금방 찾을 수 있음.
	문제 예시를 보면

	7
	3 8
	8 1 0
	2 7 4 4
	4 5 2 6 5

	4번 째 줄의 2를 거친다면, 그 아랫줄의 4로 가는거보다는 5로 가는게 무조건 크다. 따라서 그자리에 2+5 = 7을 넣는다.
	그 옆의 7을 거친다면 2보다는 5로가는게 크다. 따라서 그자리에 7+5 = 12를 넣는다.
	그 옆의 4, 4도 같은 방식으로 처리를 한다.
	3번째 줄의 7을 거친다면, 2(아래쪽 합계값 7)쪽으로 가는것 보다 7(아래쪽 합계값 12)로 가는게 무조건 크다. 따라서 12+8 = 20을 넣는다.
	이런식으로 따라 올라가면 최적의 루트와 최대 값을 구할 수 있다.
	 */

	public int solution(int[][] triangle) {

		for(int i=triangle.length-2; i>=0; i--){
			for(int j=0; j<triangle[i].length; j++){
				triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
			}
		}

		return triangle[0][0];
	}
}