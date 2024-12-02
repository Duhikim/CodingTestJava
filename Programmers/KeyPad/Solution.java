package CodingTestStudy.KeyPad;

class Solution {
	public int getDistance(int num1, int num2) {
		if(num1 == num2) return 0;
		if(Math.abs(num1-num2) == 1 
			|| Math.abs(num1-num2) == 3) {
			return 1;
		}
		if(Math.abs(num1-num2) == 2 
				|| Math.abs(num1-num2) == 4
				|| Math.abs(num1-num2) == 6) {
				return 2;
			}	
		if(Math.abs(num1-num2) == 5 
				|| Math.abs(num1-num2) == 7
				|| Math.abs(num1-num2) == 9) {
				return 3;
			}
		if(Math.abs(num1-num2) == 8 
				|| Math.abs(num1-num2) == 10) {
				return 4;
			}		
		
		System.out.println("Bug check");
		return -1;
	}
	
    public String solution(int[] numbers, String hand) {
        
    	String answer = "";
        StringBuilder sb = new StringBuilder();
        int leftHand = 10;
        int rightHand = 12;
        
        for(int num: numbers) {        	
        	if(num%3 == 1) { 
        		sb.append("L");
        		leftHand = num;
        		continue;
        	}
        	else if(num%3 == 0 && num != 0) {
        		sb.append("R");
        		rightHand = num;
        		continue;
        	}
        	else {
        		int num_temp = (num==0)? 11: num;
        		int l = getDistance(num_temp, leftHand);
        		int r = getDistance(num_temp, rightHand);
        		if (l==r) {        			
        			if(hand.equals("right")) {        				
        				sb.append("R");
        				rightHand = num_temp;        				
        			}
        			else {
        				sb.append("L");
        				leftHand = num_temp;        				
        			}
        			continue;
        		}
        		else {
        			if (l>r) {
            			sb.append("R");
                		rightHand = num_temp;
            		}
            		else {
            			sb.append("L");
                		leftHand = num_temp; 
            		}
        			continue;
        		}
        	}
        }
        
        answer = sb.toString();
        return answer;
    }
    public static void main(String[] args) {
		Solution sol = new Solution();
    	int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		String expected = "LRLLLRLLRRL";
		String calculated = sol.solution(numbers, hand);
		System.out.println("예상 값: " + expected);
		System.out.println("계산 값: " + calculated);
		if(expected.equals(calculated)) System.out.println("Correct!");
		else System.out.println("Wrong!");
	}
}