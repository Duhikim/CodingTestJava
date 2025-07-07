package CodingTestStudy.SealedOrder;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();

        long n =
//        30;
        7388;
        String[] bans =
//        {"d", "e", "bb", "aa", "ae"};
        {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"};

        System.out.println(sol.solution(n, bans));

    }

    public String solution(long n, String[] bans) {
        long modifiedN = n;

        long[] bansByNum = new long[bans.length];
        for(int i=0; i<bans.length; i++){
            bansByNum[i] = getNumber(bans[i]);
        } Arrays.sort(bansByNum);


        for(int i=0; i<bansByNum.length; i++){
            if(bansByNum[i] < modifiedN){
                modifiedN++;
            } else if(bansByNum[i] == modifiedN){
                while(i<bansByNum.length && bansByNum[i] == modifiedN){
                    modifiedN++;
                    i++;
                }
                break;
            } else{
                break;
            }
        }

        return findOrderByNum(modifiedN);
    }

    public long getNumber(String order){
        long answer = 0;
        int len = order.length();

        for(int i=0; i<len; i++){
            answer += power(26, i);
            answer += (long)(order.charAt(i) - 'a') * power(26, (len-i-1));
        }

        return answer;
    }

    public String findOrderByNum(long num){
        StringBuilder sb = new StringBuilder();

        while(num > 0){
            num--;
            sb.append((char)('a' + num % 26));
            num /= 26;
        }
        return sb.reverse().toString();
    }

    public long power(int a, int b){
        long result = 1;
        long base = a;

        while(b > 0){
            if((b&1) == 1) result *= base;
            base *= base;
            b >>= 1;
        }
        return result;
    }

}