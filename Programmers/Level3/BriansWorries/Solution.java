package CodingTestStudy.Level3.BriansWorries;

import java.util.*;

public class Solution {

    /*
    (규칙 1) 특정 단어를 선택하여 글자 사이마다 같은 기호를 넣는다. ex) HELLO -> HaEaLaLaO
    (규칙 2) 특정 단어를 선택하여 단어 앞뒤에 같은 기호를 넣는다. ex) WORLD -> bWORLDb
    위의 두 가지 규칙은 한 단어에 모두 적용될 수 있지만 같은 규칙은 두 번 적용될 수 없다.
    한 번 쓰인 소문자(특수기호)는 다시 쓰일 수 없다.
     */

    /*
    풀이)
    규칙에 어긋나는지 판별하면서 하려니 복잡해서, 그냥 맞다고 가정하고 풀고 마지막에 검증함.
    소문자나 특수문자를 통틀어서 특수문자라고 표기함.
    각 특수문자별로 개수를 센다.
    특수문자 개수가 2가 아니면 무조건 규칙 1이 적용된 것이다.
    특수문자 개수가 2면 규칙1일수도있고 규칙2일수도 있는데, 그냥 규칙2로 단정하고 풀어도 괜찮을것 같음.

    규칙2가 적용됐으면 그 특수문자는 space로 변환하면 된다.
    규칙 1이 적용됐으면 그 특수문자들을 전부 지우고 앞뒤로 space를 추가하면 된다.
    최종적으로 space가 두칸이상이면 한칸으로 줄인다.

     */

    public String solution(String sentence) {
        Queue<Character> dq = new ArrayDeque<>();
        Map<Character, Integer> map = new HashMap<>();
        List<char[]> rules = new ArrayList<>();


        StringBuilder answer = new StringBuilder();
        answer.append(sentence);

        for(int i=0; i<sentence.length(); i++){
            char letter = sentence.charAt(i);
            if(letter < 'A' || letter > 'Z') {
                if(letter == ' ') return "invalid";
                dq.add(letter);
                map.put(letter, map.getOrDefault(letter, 0) + 1);
            }
        }

        while(!dq.isEmpty()){
            char letter = dq.poll();
            if(map.get(letter) != 2){
                for(int i=1; i<map.get(letter); i++){
                    if(dq.poll() != letter) return "invalid";
                }
                rules.add(new char[]{letter, '1'});// rule 1로 저장
            }else{ // 특수문자가 두개일 때, 그 특수문자 사이에 아무것도 없거나 한가지 종류의 특수문자만 있어야 함.
                char letter2 = dq.poll();
                if(letter!=letter2) {
                    for(int i=1; i<map.get(letter2); i++){
                        if(dq.poll() != letter2) return "invalid";
                    } if(dq.poll() != letter) return "invalid";
                    rules.add(new char[]{letter2, '1'});// rule 1로 저장
                    rules.add(new char[]{letter, '2'});// rule 2로 저장
                }
                else{
                    for(int i=0; i<sentence.length(); i++){
                        if(sentence.charAt(i) != letter) continue;
                        if(i+3 < sentence.length()
                            && sentence.charAt(i+2) == letter
                            && i-1 >= 0 && sentence.charAt(i-1) >= 'A' && sentence.charAt(i-1) <= 'Z'
                            && sentence.charAt(i+3) >= 'A' && sentence.charAt(i+3) <= 'Z'){
                            rules.add(new char[]{letter, '1'});
                            break;
                        }
                        else{
                            rules.add(new char[]{letter, '2'});
                            break;
                        }
                    }
                }

            }
        }

        for(int i=0; i<rules.size(); i++){
            int start=-1, end=-1;
            char[] rule = rules.get(i);
            if(rule[1] == '1'){
                for(int j=0; j<answer.length(); j++){
                    if(answer.charAt(j) == rule[0]){
                        start = j-1;
                        break;
                    }
                }
                end = start + 2*map.get(rule[0]);
                if(start<0 || end >= answer.length()
                        || answer.charAt(start) < 'A'
                        || answer.charAt(start) > 'Z'
                        || answer.charAt(end) < 'A'
                        || answer.charAt(end) > 'Z'){
                    return "invalid";
                }
                for(int j=end-1; j>start; j-=2){
                    answer.deleteCharAt(j);
                    end--;
                }
                if(end+1 < answer.length() && answer.charAt(end+1) != ' ') answer.insert(end+1, " ");
                if(start > 0 && answer.charAt(start) != ' ') answer.insert(start, " ");

            }else{
                for(int j=0; j<answer.length(); j++){
                    if(answer.charAt(j) == rule[0]){
                        if(start == -1) start = j;
                        else end = j;
                    }
                }
                answer.replace(start, start+1, " ").replace(end, end+1, " ");
            }
        }
        String answerstr = answer.toString();

        while(answerstr.contains("  ")){
            answerstr = answerstr.replaceAll("  ", " ");
        }

        return answerstr.trim();
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

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
        int tc = 10;
        System.out.println("input : " + input[tc]);
        System.out.println(sol.solution(input[tc]));

    }

}