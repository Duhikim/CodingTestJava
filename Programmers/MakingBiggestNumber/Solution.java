package CodingTestStudy.MakingBiggestNumber;

import java.util.*;

class Solution {
    // number의 첫번째 자리부터 뒤에 k개의 숫자 중 자기보다 큰게 있으면 지우고 k를 하나 줄임
    // k가 0이될때까지 반복
    // 2중 for문을 쓰면 O(10^12)로 시간초과의 가능성이 있음.
    // Map 해결할 수 있을듯.

    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        Map<Character, Integer> numberCount = new HashMap<>();
        char max = '0';

        for(int i=0; i<=k; i++){
            char c = number.charAt(i);
            numberCount.put(c, numberCount.getOrDefault(c, 0)+1);
            if(c > max) max = c;
        }

        for(int i=0; i<number.length(); i++){
            char c = number.charAt(i);
            if(k==0) {
                answer.append(c);
                continue;
            }
            if(c==max) {
                answer.append(c);

                numberCount.put(c, numberCount.get(c) - 1);
                if(numberCount.get(c)==0) {
                    numberCount.remove(c);
                    while(!numberCount.containsKey(max) && max>0) max--;
                }

                if(i+k+1 < number.length()) {
                    char nc = number.charAt(i+k+1);
                    numberCount.put(nc, numberCount.getOrDefault(nc, 0) + 1);
                    if(nc > max) max = nc;
                }
            }else {
                k--;

                numberCount.put(c, numberCount.get(c) - 1);
                if(numberCount.get(c)==0) {
                    numberCount.remove(c);
                    while(!numberCount.containsKey(max) && max>0) max--;
                }
            }
        }

        answer.setLength(answer.length()-k);
        return answer.toString();

    }
}