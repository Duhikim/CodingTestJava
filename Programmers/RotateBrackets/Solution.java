package CodingTestStudy.RotateBrackets;
import java.util.*;

class Solution {
    // 올바른 문자열 덩어리가 몇 개인지 구해서 반환하면 될듯

    public int solution(String s) {
        int len = s.length();
        int answer = 0;

        for(int i=0; i<len; i++){
            answer = isProper(swift(s, i));
            if(answer != 0) return answer;
        }

        return answer;
    }

    public String swift(String s, int time){
        int len = s.length();
        char[] result = new char[len];
        for(int i=0; i<len; i++){
            result[i] = s.charAt((i+time) % len);
        }
        return new String(result);
    }

    public int isProper(String s){
        Deque<Character> dq = new ArrayDeque<>();
        int len = s.length();
        int answer = 0;

        for(int i=0; i<len; i++){
            char c = s.charAt(i);
            switch(c){
                case ')':
                    if(dq.isEmpty() || dq.pollLast() != '(') return 0;
                    break;
                case '}':
                    if(dq.isEmpty() || dq.pollLast() != '{') return 0;
                    break;
                case ']':
                    if(dq.isEmpty() || dq.pollLast() != '[') return 0;
                    break;

                default:
                    dq.add(c);
            }
            if(dq.isEmpty()){
                answer++;
            }
        }
        if(!dq.isEmpty()) return 0;
        return answer;
    }
}