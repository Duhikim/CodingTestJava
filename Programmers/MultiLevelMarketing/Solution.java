package CodingTestStudy.MultiLevelMarketing;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	ArrayList<Sellers> userList;
	HashMap<String, Integer> userMap;

	public class Sellers{
		public String name;
		public String parent;
		public int income;

		public Sellers(String name, String parent){
			this.name = name;
			this.parent = (parent.equals("-"))? "center": parent;
			this.income = 0;
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		sol.solution(enroll, referral, seller, amount);

	}

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {


		userList = new ArrayList<>();
		userList.add(new Sellers("center", "none"));
		userMap = new HashMap<>();
		userMap.put("center", 0);

		// HashMap과 ArrayList에 판매자 기본정보 등록
		for(int i=0; i<enroll.length; i++){
			Sellers seller_ = new Sellers(enroll[i], referral[i]);
			userList.add(seller_);
			userMap.put(enroll[i], userMap.size());
		}

		for(int i=0; i<seller.length; i++){
			int idx = userMap.get(seller[i]);
			int income = amount[i] * 100;
			divideIncome(idx, income);
		}

		int[] answer = new int[enroll.length];

		for(int i=0; i<enroll.length; i++){
			answer[i] = userList.get(i+1).income;
		}
		return answer;
	}
	public void divideIncome(int sellerIdx, int income){
		Sellers seller_ = userList.get(sellerIdx);
		int incentive = income/10;
		seller_.income += income - incentive;
		if(seller_.name.equals("center") || incentive==0) {
			seller_.income += incentive;
			return;
		}
		int parentIdx = userMap.get(seller_.parent);
		divideIncome(parentIdx, incentive);
	}
}

