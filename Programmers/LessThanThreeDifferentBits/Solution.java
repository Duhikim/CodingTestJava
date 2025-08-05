package CodingTestStudy.LessThanThreeDifferentBits;

class Solution {
    // 첫 0이 어딘지 체크한 후 첫 0을 1로 바꾸고 그 바로 다음 비트(원래1)을 0으로 바꾸고 나머지는 그대로 1.
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i=0; i<numbers.length; i++){
            long bit = 1L << numberOfOnes(numbers[i]);
            answer[i] = (numbers[i] | (bit)) & ~(bit>>1);
        }

        return answer;
    }

    public int numberOfOnes(long num){
        num = ~num;
        int n = 63;
        long y;
        int div = 32;

        while(div > 0){
            y = num << div; if(y!=0) { n -= div; num = y; }
            div >>= 1;
        }
        return n;
    }
}