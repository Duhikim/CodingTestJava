package CodingTestStudy.Level2.Cache;

import java.util.LinkedList;

public class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LinkedList<String> cache = new LinkedList<String>();

        for(String city : cities){
            city = city.toUpperCase();
            if(cache.contains(city)){
                cache.remove(city);
                cache.addLast(city);
                answer += 1;
            }
            else{
                cache.addLast(city);
                answer += 5;
            }
            if(cache.size() == cacheSize + 1){
                cache.removeFirst();
            }
        }

        return answer;
    }
}