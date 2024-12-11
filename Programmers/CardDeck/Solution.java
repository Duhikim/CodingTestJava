package CodingTestStudy.CardDeck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
public String solution(String[] cards1, String[] cards2, String[] goal) {
   
    
    ArrayDeque<String> deck1 = Arrays.stream(cards1)
            .collect(Collectors.toCollection(ArrayDeque::new));
    ArrayDeque<String> deck2 = Arrays.stream(cards2)
            .collect(Collectors.toCollection(ArrayDeque::new));
    
    for(String word: goal){
        if(!deck1.isEmpty() && deck1.peekFirst().equals(word)) deck1.pollFirst();
        else if(!deck2.isEmpty() && deck2.peekFirst().equals(word)) deck2.pollFirst();
        else {return "No"; }
    }
    return "Yes";
}
}