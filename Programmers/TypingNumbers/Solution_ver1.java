package CodingTestStudy.TypingNumbers;

import java.util.ArrayDeque;

class Solution_ver1 {
class Hands{
    int left;
    int right;
    public Hands(int left, int right){
        this.left = left;
        this.right = right;
    }
}
public static int[][] cost = { // cost[a][b]는 a(번호)에서 b(번호)로 갈때 발생하는 비용.
        {1, 7, 6, 7, 5, 4, 5, 3, 2, 3}, // 0에서 출발
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6}, // 1에서 출발
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5}, // 2에서 출발
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4}, // 3에서 출발
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5}, // 4에서 출발
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3}, // 5에서 출발
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2}, // 6에서 출발
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4}, // 7에서 출발
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2}, // 8에서 출발
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1} };// 9에서 출발

public int solution(String numbers) {
    
    int[][][] table = new int[100001][10][10];
    for(int i = 0; i < 100001; i++){
        for(int j = 0; j < 10; j++){
            for(int k = 0; k < 10; k++){
                table[i][j][k] = Integer.MAX_VALUE;
            }
        }
    }
    
    int left=4, right=6;
    table[0][left][right] = 0;
    ArrayDeque<Hands> q = new ArrayDeque<>();
    Hands hand = new Hands(left, right);
    q.add(hand);
    
    for(int turn = 1; turn <= numbers.length(); turn++){
        int num = numbers.charAt(turn-1) & 15;
        int size = q.size();
        while(size-- > 0){
            hand = q.pollFirst();
            left = hand.left;
            right = hand.right;
            if(num != left) {
                table[turn][left][num] = Math.min(table[turn-1][left][right] + cost[right][num], table[turn][left][num]);
                q.add(new Hands(left, num));
            }
            if(num != right) {
                table[turn][num][right] = Math.min(table[turn-1][left][right] + cost[left][num], table[turn][num][right]);
                q.add(new Hands(num, right));
            }
        }
    }
    int answer = Integer.MAX_VALUE;
    int last = numbers.length();
    for(Hands lastHand: q){
        answer = Math.min(answer, table[last][lastHand.left][lastHand.right]);
    }
    
    return answer;
}
}