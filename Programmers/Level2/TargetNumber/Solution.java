package CodingTestStudy.Level2.TargetNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
public static void main(String[] args) {
    Solution sol = new Solution();
    int[] numbers = {1, 1, 1, 1, 1};
    int target = 3;
    int expected = 5;
    int calculated = sol.solution(numbers, target);
    System.out.println("예상 값: " + expected);
    System.out.println("계산 값: " + calculated);
    if(expected == calculated) System.out.println("Correct!");
    else System.out.println("Wrong!");
    
    numbers = new int[]{4, 1, 2, 1};
    target = 4;
    expected = 2;
    calculated = sol.solution(numbers, target);
    System.out.println("예상 값: " + expected);
    System.out.println("계산 값: " + calculated);
    if(expected == calculated) System.out.println("Correct!");
    else System.out.println("Wrong!");
}
public int solution(int[] numbers, int target) {
    int[] answer = {0};
    int sum=0;
    
    for(int n: numbers){
        sum+=n;
    }
    
    int gap = sum - target;
    ArrayList<Integer> numsList = Arrays.stream(numbers)
            .filter(n->n<=gap)
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    
    
    makeCombination(0, numsList, gap/2, answer);
    
    return answer[0];
}
public static void makeCombination(int idx, ArrayList<Integer> numsList, int gap, int[] answer){
   if(idx == numsList.size()){
       if(gap==0) answer[0]++;
       return;
   }
   if(gap < 0) return;
   
   makeCombination(idx+1, numsList,  gap - numsList.get(idx), answer);
   makeCombination(idx+1, numsList,  gap, answer);
   
}
}