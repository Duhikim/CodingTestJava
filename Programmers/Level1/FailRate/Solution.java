package CodingTestStudy.Level1.FailRate;
//ver 2
import java.util.ArrayList;
import java.util.HashMap;

class Solution {	
	
	public static void main(String[] args) {

		int N1 = 5;
		int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] result1 = {3,4,2,1,5};
		solution(N1, stages1);


		int N2 = 4;
		int[] stages2 = {4,4,4,4,4}	;
		int[] result2 = {4,1,2,3};
		solution(N2, stages2);

		}
	
	
    public static int[] solution(int N, int[] stages) {
        
    	/*****************
    	 스테이지 n의 실패율은
    	 stages에 들어있는 숫자들 중, 
    	 n의 개수 / n 이상인 수의 개수
    	 로 구할 수 있다.
    	 스테이지 1부터 차례로 구해서 위치 탐색해서 끼워 넣으면 될듯
    	 
    	 ****************/
    	
    	
    	
    	HashMap<Integer, Double> failRate = new HashMap<>();
    	ArrayList<Integer> sortedNum = new ArrayList<>();
    	
    	for(int i=1; i<=N; i++) {
    		failRate.put(i,  getFailRate(i, stages));
    		insertNumIntoList(i, failRate, sortedNum, 0, sortedNum.size()-1);
    	}    	
    	
//    	System.out.println(failRate);
//    	System.out.println(sortedNum);
//    	
    	
    	int[] answer = new int[sortedNum.size()];
    	for(int i=0; i<sortedNum.size(); i++) {
    		answer[i] = sortedNum.get(i);
    	}
        return answer;
    }
    
    public static double getFailRate(int n, int[] stages) {
    	double a = 0, b=0; // a는 n의 개수, b는 n이상인 수의 개수
    	for(int stage: stages) {
    		if(stage==n) a++;
    		if(stage >= n) b++;
    	}
    	return (b!=0)? a/b: 0;
    }
    public static void insertNumIntoList(int num, HashMap<Integer, Double> failRate, ArrayList<Integer> sortedNum, int s, int e) {
    	if(s > e) {
    		sortedNum.add(s, num);
    		return;
    	}
    	if(s == e) {
    		if(failRate.get(num) > failRate.get(sortedNum.get(e)) ||
    				((failRate.get(num) == failRate.get(sortedNum.get(e)))&&(num < e))
    				) {
    			sortedNum.add(e, num);
        		return;
    		}
    		else {
    			sortedNum.add(e+1, num);
        		return;
    		}
    	}
    	if(s == e-1) {
    		if(failRate.get(num) > failRate.get(sortedNum.get(s)) ||
    				((failRate.get(num) == failRate.get(sortedNum.get(s)))&&(num < s))
    				) {
    			sortedNum.add(s, num);
    			return;
    		}
    		if(failRate.get(num) > failRate.get(sortedNum.get(e)) ||
    				((failRate.get(num) == failRate.get(sortedNum.get(e)))&&(num < e))
    				) {
    			sortedNum.add(e, num);
    			return;
    		}
    		sortedNum.add(e+1, num);
			return;
    	}
    	else {
    		int mid = (s+e)/2;
    		if( failRate.get(num) > failRate.get(sortedNum.get(mid)) ||
    				((failRate.get(num) == failRate.get(sortedNum.get(mid))) && (num < sortedNum.get(mid))) 
    				) {
    			insertNumIntoList(num, failRate, sortedNum, s, mid);
    			return;
    		}
    		else {
    			insertNumIntoList(num, failRate, sortedNum, mid, e);
    			return;
    		}    		
    	}    	
    }
}