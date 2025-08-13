package CodingTestStudy.Level2.MenuRenewal;

import java.util.*;

public class Solution {



	public String[] solution(String[] orders, int[] course) {

		ArrayList<String> answer = new ArrayList<>();

		// 만들어진 ordersArr를 n글자씩 조합을 만들어 HashMap에 넣으면서 카운팅하려고 한다.
		// n과 orderArr를 인수로 받아 조합을 만들어 HashMap에 넣는 함수를 만들자.
		// 그러기 위해서 HashMap은 외부에 선언하고 함수에서 인수로 받으면 된다.
		// 한 조합(예: AB, XYZ)도 밖에서 선언하여 인수로 넣어준다.
		// 함수는 깊이우선탐색 방식으로 구현.
		for(int i=0; i<course.length; i++){
			HashMap<String, Integer> ansMap = new HashMap<>();
			for(int j=0; j<orders.length; j++) {
				char[] combination = new char[course[i]];
				makeCombination(0, 0, orders[j], course[i], combination, ansMap);
			}
			// 한개의 HashMap 완성됐으니, value값이 가장 큰것(들)을 찾아서 key값을 저장. 선형 탐색으로 한다.
			int maxValue=0;
			ArrayList<String> subAns = new ArrayList<>();
			for(Map.Entry<String, Integer> es : ansMap.entrySet()){
				if(es.getValue() > 1){
					if(es.getValue() > maxValue){
						maxValue = es.getValue();
						subAns.clear();
						subAns.add(es.getKey());
					}
					else if(es.getValue() == maxValue){
						subAns.add(es.getKey());
					}
				}
			}
			answer.addAll(subAns);
		}


		return answer.stream()
				.sorted()
				.toArray(String[]::new);
	}

	public void makeCombination(int idx, int start, String menus, int n, char[] combination, HashMap<String, Integer> ansMap){
		if(idx == n){
			char[] sortedComb = combination.clone();
			Arrays.sort(sortedComb);
			StringBuilder sb = new StringBuilder();
			for(char c : sortedComb){
				sb.append(c);
			}
			String str = sb.toString();
			ansMap.put(str, ansMap.getOrDefault(str, 0) + 1);
			return;
		}

		for(int i=start; i< menus.length(); i++){
			combination[idx] = menus.charAt(i);
			makeCombination(idx+1, i+1, menus, n, combination, ansMap);
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = {2,3,4};
		String[] results = sol.solution(orders, course);
		for(String result: results){
			System.out.println(result);
		}
	}
}