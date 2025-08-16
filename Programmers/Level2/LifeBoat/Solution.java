package CodingTestStudy.Level2.LifeBoat;

import java.util.*;

class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;
        int n = people.length;
        Arrays.sort(people);
        int light = 0;
        int heavy = n-1;

        while(light <= heavy){
            if(people[light] + people[heavy] <= limit){
                light++;
            }
            heavy--;
            answer++;
        }
        return answer;
    }
}