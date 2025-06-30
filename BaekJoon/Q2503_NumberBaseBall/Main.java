package BaekJoon.Q2503_NumberBaseBall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        Set<String> answer = new HashSet<>();

        String[] conditions = new String[testCase];
        for(int i = 0; i < testCase; i++) {
            conditions[i] = br.readLine();
        }

        // 1~9의 숫자 중 3개를 뽑아 나열한 숫자, 즉 9P3개의 경우의 수가 있음. (9*8*7 = 504)
        // 이 모든 숫자를 조건 검사하여 전부 통과한것만 필터링

        Set<String> permutation = new HashSet<String>();
        for(int i=123; i<=987; i++){
            if(i/100 == 0 || (i%100)/10 == 0 || i%10 == 0) continue;
            if(i/100 == (i%100)/10
                    || i/100 == (i%10)
                    || (i%100)/10 == (i%10)) continue;
            permutation.add(Integer.toString(i));
        }

        for(String s : permutation) {
            if(conditionCheck(conditions, s)) answer.add(s);
        }

        System.out.println(answer.size());
    }

    public static boolean conditionCheck(String[] conditions, String answer) {

        for(String condition : conditions) {
            int strike =0, ball = 0;
            String[] s = condition.split(" ");
            if(s[0].charAt(0) == answer.charAt(0)) strike++;
            if(s[0].charAt(1) == answer.charAt(1)) strike++;
            if(s[0].charAt(2) == answer.charAt(2)) strike++;
            if(answer.charAt(0) == s[0].charAt(1) || answer.charAt(0) == s[0].charAt(2)) ball++;
            if(answer.charAt(1) == s[0].charAt(0) || answer.charAt(1) == s[0].charAt(2)) ball++;
            if(answer.charAt(2) == s[0].charAt(0) || answer.charAt(2) == s[0].charAt(1)) ball++;
            if(strike != Integer.parseInt(s[1]) || ball != Integer.parseInt(s[2])) return false;
        }
        return true;
    }
}
