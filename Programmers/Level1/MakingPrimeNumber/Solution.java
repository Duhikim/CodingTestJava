package CodingTestStudy.Level1.MakingPrimeNumber;

public class Solution {
    // 50C3 = 100 49 4 < 100 50 4 = 100 200 = 20,000
    int[] nums;
    int answer = 0;
    public int solution(int[] nums) {
        this.nums = nums;
        combination(0, 0, 0);
        return answer;
    }

    public void combination(int cycle, int num, int startIdx){
        if(cycle==3){
            if(isPrime(num)) answer++;
            return;
        }

        for(int i=startIdx; i<nums.length; i++){
            combination(cycle+1, num+nums[i], i+1);
        }
    }
    public boolean isPrime(int num){
        for(int i=2; i*i <= num; i++){
            if((num % i)==0) return false;
        }
        return true;
    }
}