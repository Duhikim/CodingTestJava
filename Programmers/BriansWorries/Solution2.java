package CodingTestStudy.BriansWorries;

import java.util.*;

public class Solution2 {
    public String solution(String sentence) {
        Queue<Character> dq = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
//        Set<Character> used = new HashSet<>();

        StringBuilder answer = new StringBuilder(sentence);

        for(int i=0; i<answer.length(); i++){
            char letter = answer.charAt(i);
            if(letter < 'A' || letter > 'Z') {
                if(letter == ' ') return "invalid";
                dq.add(letter);
                map.put(letter, map.getOrDefault(letter, 0) + 1);
            }
        }

        for(int i=0; i<answer.length(); i++){
            int start = -1, end = -1;
            char letter = answer.charAt(i);
            if((letter < 'A' || letter > 'Z') && map.get(letter) != 2) {
//                used.add(letter);
                start = i-1;
                end = i;
                if(start < 0 || answer.charAt(start) < 'A' || answer.charAt(start) > 'Z') return "invalid";
                for(int j=1; j<map.get(letter); j++){
                    end += 2;
                    if(answer.charAt(end) != letter) return "invalid";
                }
                end++;
                if(end >= answer.length()) return "invalid";

                if(start>0 && end < answer.length()-1
                        && answer.charAt(start-1) == answer.charAt(end+1)){
                    char letter2 = answer.charAt(start-1);
                    if(map.get(letter2) != 2) return "invalid";
//                    used.add(letter2);
                    start -= 1;
                    end += 1;
                }

                StringBuilder word = new StringBuilder();
                for(int j = start; j<= end; j++){
                    if(answer.charAt(j) < 'A' || answer.charAt(j) > 'Z') continue;
                    word.append(answer.charAt(j));
                }
                word.append(" ");
                word.insert(0, " ");

                answer.replace(start, end+1, word.toString());

                i = start + word.length() - 1;
            }

        }

        for(int i=0; i<answer.length(); i++){
            int start = -1, end = -1;
            char letter = answer.charAt(i);
            Character innerLetter = null;
            int innerStart = -1, innerEnd = -1;
            if((letter < 'A' || letter > 'Z') && letter != ' '){
                start = i;
                for(int j=start+1; j<answer.length(); j++){
                    char letter2 = answer.charAt(j);
                    if(letter2 >= 'A' && letter2 <= 'Z') continue;
                    if(letter != letter2){
                        if(innerLetter == null){
                            innerLetter = letter2;
                            innerStart = j;
                        } else if(letter2 != innerLetter) return "invalid";
                        else{
                            innerEnd = j;
                            if(innerStart+2 != innerEnd) return "invalid";
                        }
                    }else {
                        end = j;
                        break;
                    }
                }

                if(innerStart != -1){
                    StringBuilder word = new StringBuilder();
                    for(int j=start+1; j<end; j++){
                        if(answer.charAt(j) < 'A' || answer.charAt(j) > 'Z') continue;
                        word.append(answer.charAt(j));
                    }
//                    if(end < answer.length()-1) word.append(" ");
                    word.append(" ");
                    word.insert(0, " ");

                    answer.replace(start, end+1, word.toString());
                    i = start + word.length() -1;

                }else{
                    StringBuilder word = new StringBuilder();
                    if(start + 2 > end) return "invalid";
                    else if(start+2 < end){
                        word.append(answer, start+1, end);
                    }
                    else{ // 특수문자 두글자에 두칸 떨어져있음. 따라서 규칙 1, 규칙 2 둘다 적용 가능.
                        // 먼저 규칙 1을 적용해보고 invalid인 경우 규칙 2 적용.
                        if(start>0 && answer.charAt(start-1) >= 'A' && answer.charAt(start-1) <= 'Z'
                            && end < answer.length()-1 && answer.charAt(end+1) >= 'A' && answer.charAt(end+1) <= 'Z'
                        ){
                            start--;
                            end++;
                        }
                        for(int j=start; j<=end; j++){
                            if(answer.charAt(j) < 'A' || answer.charAt(j) > 'Z') continue;
                            word.append(answer.charAt(j));
                        }

                    }
                    word.append(" ");
                    word.insert(0, " ");

                    answer.replace(start, end+1, word.toString());
                    i = start + word.length() -1;
                }
            }
        }

        String answerstr = answer.toString();
        while(answerstr.contains("  ")){
            answerstr = answerstr.replaceAll("  ", " ");
        }

        return answerstr.trim();

    }

    public static void main(String[] args) {

        Solution2 sol = new Solution2();

        String[] input = {
                "HaEaLaLaObWORLDb", // HELLO WORLD
                "SpIpGpOpNpGJqOqA", // SIGONG JOA
                "AxAxAxAoBoBoB", // invalid
                "aHELLOa bWORLDb", // invalid
                "aHbEbLbLbOacWdOdRdLdDc", // HELLO WORLD
                "HaEaLaLObWORLDb", // HELL O WORLD
                "aHELLOWORLDa", // HELLOWORLD
                "HaEaLaLaOWaOaRaLaD", // invalid
                "abHELLObaWORLD", // invalid
                "AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR",
                "AaAaAcA" // A A AA
        };
        for(int tc = 0; tc < input.length; tc++){
            System.out.println("input : " + input[tc]);
            System.out.println(sol.solution(input[tc]));
        }

    }

}
