package CodingTestStudy.FailRate;

import java.util.ArrayList;
import java.util.HashMap;

class Solution_ver1 {	
	
	
    public static int[] solution(int N, int[] stages) {
        
    	/*****************
    	 스테이지 n의 실패율은
    	 stages에 들어있는 숫자들 중, 
    	 n의 개수 / n 이상인 수의 개수
    	 로 구할 수 있다.
    	 스테이지 1부터 차례로 구해서 위치 탐색해서 끼워 넣으면 될듯
    	 
    	 ****************/
    	
    	
    	
    	HashMap<Integer, Double> fail_Rate = new HashMap<>();
    	ArrayList<Integer> sortedNum = new ArrayList<>();
    	
    	for(int i=1; i<=N; i++) {
    		fail_Rate.put(i,  failRate(i, stages));
    		insertNumIntoList(i, fail_Rate, sortedNum);
    	}    	
    	
    	int[] answer = new int[sortedNum.size()];
    	for(int i=0; i<sortedNum.size(); i++) {
    		answer[i] = sortedNum.get(i);
    	}
        return answer;
    }
    
    public static double failRate(int n, int[] stages) {
    	double a = 0, b=0; // a는 n의 개수, b는 n이상인 수의 개수
    	for(int stage: stages) {
    		if(stage==n) a++;
    		if(stage >= n) b++;
    	}
    	return (b!=0)? a/b: 0;
    }
    public static void insertNumIntoList(int num, HashMap<Integer, Double> fail_Rate, ArrayList<Integer> sortedNum) {
    	int _size = sortedNum.size();
    	if(_size == 0) {
    		sortedNum.add(num);
    		return;
    	}
    	for(int i=0; i<_size; i++) {
    		if( fail_Rate.get(num) > fail_Rate.get(sortedNum.get(i)) ) {
    			sortedNum.add(i, num);
    			return;
    		}
    		if( fail_Rate.get(num) == fail_Rate.get(sortedNum.get(i)) ) {
    			if(num < sortedNum.get(i)) {
    				sortedNum.add(i, num);
        			return;
    			}    			    			
    		}    		
    		if(i == _size-1) {
    			sortedNum.add(num);
    			return;
    		}    		
    	}
    }
}