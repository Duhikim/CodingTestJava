package CodingTestStudy.Level2.Tuple;

import java.util.*;

class Solution {
    public int[] solution(String s) {

        List<Set<Integer>> list = makeList(s);
        list.sort((a, b)-> a.size() - b.size());

        int sz = list.size();
        int[] answer = new int[sz];

        for(int i=sz-1; i>0; i--){
            list.get(i).removeAll(list.get(i-1));
            if(list.get(i).size() != 1) {
                System.out.println("Unexpected result");
                break;
            }
            answer[i] = list.get(i).iterator().next();
        } answer[0] = list.get(0).iterator().next();


        return answer;
    }

    public List<Set<Integer>> makeList(String s){
        List<Set<Integer>> result = new ArrayList<>();
        Set<Integer> inner = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<s.length()-1; i++){
            char c = s.charAt(i);
            if(c=='{'){
                continue;
            } else if(c=='}'){
                inner.add(Integer.parseInt(sb.toString()));
                result.add(inner);
                sb = new StringBuilder();
                inner = new HashSet<>();
            } else if(c == ','){
                if(sb.length()==0) continue;
                inner.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            } else{
                sb.append(c);
            }
        }

        return result;
    }
}