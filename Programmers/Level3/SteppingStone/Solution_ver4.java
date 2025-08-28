package CodingTestStudy.Level3.SteppingStone;

import java.util.*;
class Solution_ver4 {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i=0; i<stones.length; i++){
            int newstone = stones[i];

            while(!dq.isEmpty() && stones[dq.peekLast()] <= newstone){
                dq.pollLast();
            }
            dq.add(i);

            if(dq.peek() <= i-k) dq.poll();

            if(i >= k-1) answer = Math.min(answer, stones[dq.peek()]);
        }

        return answer;
    }
}