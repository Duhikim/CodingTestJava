package CodingTestStudy.Level2.GetNumberOfPrimeNumbers;

import java.lang.Math;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String convertedNumber;
        if(k==10) convertedNumber = Integer.toString(n);
        else convertedNumber = convertBase(n, k);

        String[] strSplit = convertedNumber.split("0");

        for(String str: strSplit){
            if(str.isEmpty()) continue;
            int num = Integer.parseInt(str);
            if(isPrime(num)) answer++;
        }

        return answer;
    }

    public String convertBase(int num, int base){
        StringBuilder sb = new StringBuilder();

        while(num > 0){
            sb.append((char)(num%base + '0'));
            num /= base;
        }
        return sb.reverse().toString();
    }

    public boolean isPrime(int num){
        if(num <= 1) return false;
        int sqrt = (int)Math.sqrt(num);
        for(int i=2; i<=sqrt; i++){
            if(num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println("Expected: 3, Calculated : " + sol.solution(437674, 3));
        System.out.println("Expected: 2, Calculated : " + sol.solution(110011, 10));
    }
}