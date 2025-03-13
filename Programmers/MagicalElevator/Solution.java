package CodingTestStudy.MagicalElevator;

public class Solution {
	public int solution(int storey) {
		// 각 자리에서 4이하인 경우 아래서 올라오고 6이상인 경우 위에서 내려온다.
		// 5인 경우
		// 그 후 각 자리 수를 더해주면 된다.

		int convertedStorey = storeyConvert(storey);
		int answer = 0;
		while(convertedStorey > 0){
			answer += (convertedStorey % 10);
			convertedStorey /= 10;
		}
		return answer;
	}
	public int storeyConvert(int storey){
		int result = 0;
		int exp = 0;

		while(storey > 0){
			if((storey % 10) > 5 || ((storey % 10) == 5 && (storey/10 % 10) >= 5)){
				storey += 10;
				result +=  (10-(storey % 10)) * (int) Math.pow(10, exp);
			}
			else{
				result +=  (storey % 10) * (int) Math.pow(10, exp);
			}
			exp++;
			storey /= 10;
		}
		return result;
	}
}