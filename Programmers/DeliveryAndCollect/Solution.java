package CodingTestStudy.DeliveryAndCollect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int cap=1;
		int n = 5;
		int[] deliveries = {0, 0, 1, 0, 0};
		int[] pickups = {0, 0, 0, 0, 0};
		sol.solution(cap, n, deliveries, pickups);
	}

	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		ArrayList<Integer> deliveries_ = Arrays.stream(deliveries)
				.boxed()
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Integer> pickups_ = Arrays.stream(pickups)
				.boxed()
				.collect(Collectors.toCollection(ArrayList::new));

		while (!deliveries_.isEmpty() && deliveries_.get(deliveries_.size() - 1) == 0) {
			deliveries_.remove(deliveries_.size() - 1);
		}
		while (!pickups_.isEmpty() && pickups_.get(pickups_.size() - 1) == 0) {
			pickups_.remove(pickups_.size() - 1);
		}

		while(!deliveries_.isEmpty() || !pickups_.isEmpty()) {
			answer += (Math.max(deliveries_.size(), pickups_.size()))*2L;

			int crateDel = cap, cratePic = cap;

			while(crateDel-->0 && !deliveries_.isEmpty()){
				deliveries_.set(deliveries_.size()-1, deliveries_.get(deliveries_.size()-1)-1);
				while(!deliveries_.isEmpty() && deliveries_.get(deliveries_.size()-1)==0) deliveries_.remove(deliveries_.size() - 1);
			}
			while(cratePic-->0 && !pickups_.isEmpty()){
				pickups_.set(pickups_.size()-1, pickups_.get(pickups_.size()-1)-1);
				while(!pickups_.isEmpty() && pickups_.get(pickups_.size()-1)==0) pickups_.remove(pickups_.size() - 1);
			}
		}


		return answer;
	}
}