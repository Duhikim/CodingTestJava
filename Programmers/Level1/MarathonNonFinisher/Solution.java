package CodingTestStudy.Level1.MarathonNonFinisher;

import java.util.Arrays;
import java.util.HashMap;

class Solution {
public String solution(String[] participant, String[] completion) {
    String answer = "";
    
    HashMap<String, Integer> participantMap = new HashMap<>(); // participant Map
    participantMap = Arrays.stream(participant)
            .collect(HashMap::new, (map, name) -> map.put(name, map.getOrDefault(name, 0) + 1), HashMap::putAll); // participant Map 만들기
    
    for(String finisher: completion){
        participantMap.put(finisher, participantMap.get(finisher) - 1);
        if(participantMap.get(finisher) == 0){
            participantMap.remove(finisher);
        }
    }
    
    answer = participantMap.keySet().iterator().next();
    
    return answer;
}
}