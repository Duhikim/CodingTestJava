package CodingTestStudy.SecretMap;

public class Solution {
	public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        boolean[][] secMap1 = new boolean[n][n]; // 1번 지도 // true면 벽, false면 공백으로 봄.        
        boolean[][] secMap2 = new boolean[n][n]; // 2번 지도
        
        for(int i=0; i<n; i++) {
        	int num1 = arr1[i];
        	int num2 = arr2[i];
        	for(int idx=1; idx<=n; idx++) {        		
        		secMap1[i][n-idx] = (num1 % 2==1) ? true: false;        		
        		num1 /=2;
        		
        		secMap2[i][n-idx] = (num2 % 2==1) ? true: false;        		
        		num2 /=2;
        	}        	
        }               
        
        for(int i=0; i<n; i++) {
        	StringBuilder sb = new StringBuilder();
        	for(int j=0; j<n; j++) {
        		if(secMap1[i][j]==true || secMap2[i][j]==true) {
        			sb.append('#');
        		}
        		else {
        			sb.append(' ');
        		}
        	}
        	answer[i] = sb.toString();
        }        
        
        return answer;
    }
}
