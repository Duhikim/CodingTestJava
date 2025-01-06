package CodingTestStudy.EmojiSaleEvent;

/*
<목표>
1. 가입자 수 최대한 확보
2. 판매액 최대 확보
(1번 목표가 우선)

<진행 방식>
1. n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매함.
2. 이모티콘마다 할인율은 10, 20, 30, 40% 중 하나로 설정.
<구매 기준>
1. 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매함
2. 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 되면 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입함.

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution_ver1 {
	public static void main(String[] args) {
		Solution_ver1 sol = new Solution_ver1();
		int[][] users = {{40, 10000}, {25, 10000}};
		int[] emoticons = {7000, 9000};
		sol.solution(users, emoticons);
	}

	public class Users {
		int wantedRate;
		int maxPrice;
		boolean plusService;
		int totalPrice;

		public Users(int[] rateAndPrice){
			this.wantedRate = rateAndPrice[0];
			this.maxPrice = rateAndPrice[1];
			this.plusService = false;
			this.totalPrice = 0;
		}
		public void buyItem(int rate, int price){
			if(rate < wantedRate) return;
			totalPrice += price * (100 - rate) / 100;
			if(totalPrice>=maxPrice) {
				totalPrice = 0;
				plusService = true;
			}
		}
		public int[] buyItems(int[] rates, int[] prices){
			int n = rates.length;
			for(int i=0; i<n; i++){
				buyItem(rates[i], prices[i]);
			}
			int[] result = new int[2];
			result[0] = (plusService)? 1 : 0;
			result[1] = (plusService)? 0 : totalPrice;

			this.plusService = false;
			this.totalPrice = 0;

			return result;
		}
	}
	public int[] solution(int[][] users, int[] emoticons) {

		ArrayList<Users> UserList = new ArrayList<>();
		for(int i=0; i<users.length; i++){
			Users user = new Users(users[i]);
			UserList.add(user);
		}
		int[] rates = new int[emoticons.length];
		ArrayList<int[]> answers = new ArrayList<>();
		DFS(0, emoticons.length, rates, UserList, emoticons, answers);

		Collections.sort(answers, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				// 첫 번째 요소 기준 내림차순 정렬
				if (b[0] != a[0]) {
					return Integer.compare(b[0], a[0]); // 내림차순
				}
				// 첫 번째 요소가 같으면 두 번째 요소 기준 내림차순 정렬
				return Integer.compare(b[1], a[1]); // 내림차순
			}
		});


		return answers.get(0);
	}
	public void DFS(int idx, int len, int[] rates, ArrayList<Users> UserList, int[] price, ArrayList<int[]> answers){
		if(idx == len){
			int[] answer = new int[2];
			for(Users user: UserList){
				int[] temp = user.buyItems(rates, price);
				answer[0] += temp[0];
				answer[1] += temp[1];
			}
			answers.add(answer);
			return;
		}
		for(int i=1; i<=4; i++){
			rates[idx] = i*10;
			DFS(idx+1, len, rates, UserList, price, answers);
		}
	}
}