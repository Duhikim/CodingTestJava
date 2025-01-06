package CodingTestStudy.EmojiSaleEvent;

import java.util.ArrayList;

public class Solution_ver3{
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
				if(plusService) break;
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
		int[] answer = new int[2]; // 0, 0 초기화

		// User data 입력
		ArrayList<Users> UserList = new ArrayList<>();
		for(int i=0; i<users.length; i++){
			Users user = new Users(users[i]);
			UserList.add(user);
		}

		// 깊이우선탐색으로 모든 할인 조합을 ArrayList에 저장.
		int len = emoticons.length;
		ArrayList<int[]> discountRatesArray = new ArrayList();
		int[] discountRates = new int[len];
		DFS(0, len, discountRates, discountRatesArray);

		for(int[] discountRates_: discountRatesArray){
			int[] answerTemp = new int[2];
			for(Users user: UserList){
				int[] temp = user.buyItems(discountRates_, emoticons);
				answerTemp[0] += temp[0];
				answerTemp[1] += temp[1];
			}
			if(answerTemp[0] > answer[0]
					|| (answerTemp[0] == answer[0] && answerTemp[1] > answer[1])
			){
				answer[0] = answerTemp[0];
				answer[1] = answerTemp[1];
			}
		}

		return answer;
	}

	public void DFS(int idx, int len, int[] discountRates, ArrayList<int[]> discountRatesArray){
		if(idx == len){
			discountRatesArray.add(discountRates.clone());
			return;
		}
		for(int i=1; i<=4; i++){
			discountRates[idx] = i*10;
			DFS(idx+1, len, discountRates, discountRatesArray);
		}
	}
}