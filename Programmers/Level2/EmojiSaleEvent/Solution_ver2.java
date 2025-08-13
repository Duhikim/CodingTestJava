package CodingTestStudy.Level2.EmojiSaleEvent;

import java.util.ArrayList;

public class Solution_ver2 {
	public class Users {
		int wantedRate; // 유저가 원하는 할인율.
		int maxPrice; // 유저가 사용할 최대 금액.
		boolean plusService; // 서비스 가입 여부
		int totalPrice; // 사용 금액 총합

		public Users(int[] rateAndPrice){
			this.wantedRate = rateAndPrice[0];
			this.maxPrice = rateAndPrice[1];
			this.plusService = false;
			this.totalPrice = 0;
		}

		// 이모티콘의 할인율과 가격이 주어졌을 때, 유저가 이모티콘을 사는지 여부 확인 및 사는 경우 가격 계산.
		// 사용 금액이 max 이상인 경우 모두 취소하고 서비스 가입
		public void buyItem(int rate, int price){
			if(rate < wantedRate) return;
			totalPrice += price * (100 - rate) / 100;
			if(totalPrice>=maxPrice) {
				totalPrice = 0;
				plusService = true;
			}
		}
		// 이모티콘'들'의 할인율과 가격이 배열로 주어질 때, 위의 buyItem 처리
		public int[] buyItems(int[] rates, int[] prices){
			int n = rates.length;
			for(int i=0; i<n; i++){
				buyItem(rates[i], prices[i]);
				if(plusService) break; // 서비스 가입 했으면 더 이상의 계산은 필요 없음.
			}
			int[] result = new int[2];
			result[0] = (plusService)? 1 : 0;
			result[1] = (plusService)? 0 : totalPrice;

			// 다 처리해서 result에 결과 저장 했으니 다음 rates 계산을 위해 변수를 초기화해줌.
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