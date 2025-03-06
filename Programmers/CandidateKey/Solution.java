package CodingTestStudy.CandidateKey;

import java.util.*;


public class Solution {
	public int solution(String[][] relation) {
		int cardinality = relation.length;
		int degree = relation[0].length;

		Set<String> duplicateChecker = new HashSet<>();

		// 차수 자리만큼의 이진수를 생각을 해봄. 8자리면 00000000 ~ 11111111.
		// 각 자리수는 해당 자리의 속성이 포함되었다는 것을 의미.
		// 예를들어 첫번째 속성이 자체적으로 후보키가 될 때 10000000을 저장해 놓고
		// 그 이후에 속성끼리의 조합을 짤 때, 이미 후보키가 된 조합과 & 연산을 하여 후보키랑 동일하게 나오면 제외할 것이다.
		// 1010 0000(160) 이 후보키라면, 1010 1100(172)은 체크할 필요도 없는 것이다.
		// 다만, 인덱스가 0부터 시작하므로 이것을 지수로 생각하여 첫번째 속성을 1, 그다음을 2, 4, 8... 이라고 생각하는게 편할거 같다.

		ArrayList<Integer> madeCombinations = new ArrayList<>();

		int[] combinations = new int[(int)Math.pow(2, degree) - 1];
		for(int i=0; i<combinations.length; i++){
			combinations[i] = i+1;
		}

		for(int combination: combinations){

			// 최소성 체크
			boolean isExists = false; // 해당 조합이나 부분 조합이 이미 존재하는지 여부
			for(int made: madeCombinations){
				if((made & combination) == made) {
					isExists = true;
					break;
				}
			}
			if(isExists) continue;

			//유일성 체크
			ArrayList<Integer> attributesIdx = combinedAttributes(combination);
			duplicateChecker.clear();

			for(int row=0; row<cardinality; row++){
				StringBuilder sb = new StringBuilder();
				for(int idx : attributesIdx){
					sb.append(relation[row][idx]);
					sb.append(" , "); // 두 요소를 구분을 안 해주면 201, 1과 20, 11을 동일하게 처리할 수 있으므로 구분 문자를 넣어준다.
				}
				String newKey = sb.toString();
				if(duplicateChecker.contains(newKey)){
					isExists = true;
					break;
				}
				duplicateChecker.add(newKey);
			}
			if(isExists) continue;

			madeCombinations.add(combination);
		}

		return madeCombinations.size();
	}

	// 10진수 숫자를 받아 2진수로 바꿔서 필요한 attribute index를 추출하는 함수
	public ArrayList<Integer> combinedAttributes(int decimalNum){

		ArrayList<Integer> results = new ArrayList<>();

		int exp = 0;
		while(decimalNum > 0){
			if((decimalNum & 1) == 1) results.add(exp);
			decimalNum >>= 1;
			exp++;
		}

		return results;
	}
}