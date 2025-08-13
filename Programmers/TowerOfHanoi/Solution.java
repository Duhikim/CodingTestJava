package CodingTestStudy.TowerOfHanoi;

import java.util.*;

class Solution {
    // 각 탑은 LIFO의 Stack이다.
    // 3에 가장 큰것을 끼워야 한다.
    // 가장 큰것이 짝수면 짝수(2), 홀수면 홀수(1)를 먼저 놓고 하나씩 치워나가면 될듯.
    List<int[]> log;

    public int[][] solution(int n) {

        log = new ArrayList<>();
        move(1, 3, n);

        return log.stream()
                .toArray(int[][]::new);
    }

    public void move(int from, int to, int disk){
        int other = 6 - (from+to);

        if(disk==1){
            log.add(new int[]{from, to});

        } else {
            move(from, other, disk-1);
            log.add(new int[]{from, to});
            move(other, to, disk-1);
        }
    }

}