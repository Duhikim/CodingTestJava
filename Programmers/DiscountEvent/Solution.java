package CodingTestStudy.DiscountEvent;

import java.util.HashMap;

class Solution {
public int solution(String[] want, int[] number, String[] discount) {
    int answer = 0;
    HashMap<String, Integer> wantMap = new HashMap<>();
    for (int i = 0; i < want.length; i++) {
        wantMap.put(want[i], number[i]);
    }
    
    for (int i = 0; i < discount.length - 9; i++) {
        HashMap<String, Integer> tempMap = new HashMap<>(wantMap);
        for(int j=i; j<i+10; j++){
            if(tempMap.containsKey(discount[j])) {
                tempMap.put(discount[j], tempMap.get(discount[j]) - 1);
                if (tempMap.get(discount[j]) <= 0) tempMap.remove(discount[j]);
            }
        }
        if(tempMap.isEmpty())answer++;
    }
    
    return answer;
}
}