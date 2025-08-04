package CodingTestStudy.VowelDictionary;

import java.util.*;

class Solution {
    public int solution(String word) {
        // 'A', 'E', 'I', 'O', 'U' >> 0, 1, 2, 3, 4
        int answer = 0;
        int len = word.length();
        Map<Character, Integer> dict = new HashMap<>();
        dict.put('A', 0);
        dict.put('E', 1);
        dict.put('I', 2);
        dict.put('O', 3);
        dict.put('U', 4);

        int[] pages = new int[]{781, 156, 31, 6, 1};

        for(int i=0; i<len; i++){
            answer += dict.get(word.charAt(i)) * pages[i] + 1;
        }
        return answer;
    }
}