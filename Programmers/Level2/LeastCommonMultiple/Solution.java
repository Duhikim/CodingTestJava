package CodingTestStudy.Level2.LeastCommonMultiple;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	int[] primeNumbers = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};

	public int solution(int[] arr) {
		HashMap<Integer, Integer> LCM = new HashMap<>();

		for(int n: arr){
			HashMap<Integer, Integer> primes = PrimeFactorization(n);
			for(Map.Entry<Integer, Integer> es : primes.entrySet()) {
				if(LCM.containsKey(es.getKey())){
					LCM.put(es.getKey(), Math.max(es.getValue(), LCM.get(es.getKey())));
				}
				else{
					LCM.put(es.getKey(), es.getValue());
				}
			}
		}

		int answer = 1;

		for(Map.Entry<Integer, Integer> es : LCM.entrySet()){
			answer *= (int) Math.pow(es.getKey(), es.getValue());
		}


		return answer;
	}

	public HashMap<Integer, Integer> PrimeFactorization(int n){
		HashMap<Integer, Integer> primes = new HashMap<>();

		for(int pn: primeNumbers){
			if(pn > n) break;
			int time = 1;
			while(n%pn == 0 && n > 0){
				primes.put(pn, time++);
				n /= pn;
			}
		}

		return primes;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] arr = {97};
		System.out.println(sol.solution(arr));
	}
}