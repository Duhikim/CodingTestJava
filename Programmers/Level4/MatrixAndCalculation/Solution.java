package CodingTestStudy.Level4.MatrixAndCalculation;

/*
ShiftRow는 모든 줄을 아래로 내림.
Rotate는 행렬 바깥쪽 원소를 시계방향으로 회전시킴
구현자체는 매우 쉽다.
하지만 shift, rotate 모두 O(N)의 복잡도를 갖고 N은 최대 5만임
operations의 길이는 10만이니 5만*10만 = 50억이라 시간초과가 날것이다.
*/
import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        int r = rc.length;
        int c = rc[0].length;

        // 모든 데크는 위가 first, 좌가 first
        Deque<Integer> firstCol = new ArrayDeque<>();
        Deque<Integer> lastCol = new ArrayDeque<>();
        Deque<Deque<Integer>> matrix = new ArrayDeque<>();

        for(int i=0; i<r; i++){
            firstCol.add(rc[i][0]);
            lastCol.add(rc[i][c-1]);

            Deque<Integer> temp = new ArrayDeque<>();
            for(int j=1; j<c-1; j++){
                temp.add(rc[i][j]);
            }
            matrix.add(temp);
        }

        for(String op: operations){
            if(op.charAt(0)=='R'){
                // 0열에서 0행으로
                int num = firstCol.pollFirst();
                matrix.peekFirst().addFirst(num);

                // 0행에서 막열로
                num = matrix.peekFirst().pollLast();
                lastCol.addFirst(num);

                // 막열에서 막행으로
                num = lastCol.pollLast();
                matrix.peekLast().addLast(num);

                // 막행에서 0열로
                num = matrix.peekLast().pollFirst();
                firstCol.addLast(num);

            }else{
                // 세 데크 모두 마지막거를 처음으로 넣으면 됨
                firstCol.addFirst(firstCol.pollLast());
                matrix.addFirst(matrix.pollLast());
                lastCol.addFirst(lastCol.pollLast());
            }
        }

        int[][] answer = new int[r][c];
        for(int i=0; i<r; i++){
            answer[i][0] = firstCol.pollFirst();
            answer[i][c-1] = lastCol.pollFirst();

            Deque<Integer> temp = matrix.pollFirst();
            for(int j=1; j<c-1; j++){
                answer[i][j] = temp.pollFirst();
            }

        }
        return answer;
    }

}