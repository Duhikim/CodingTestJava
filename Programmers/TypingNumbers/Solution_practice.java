package CodingTestStudy.TypingNumbers;

import java.util.ArrayList;
import java.util.Collections;

class Solution_practice {
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
public static void main(String[] args){
    int[][][] table = new int[100001][10][10];
    for(int i = 0; i < 100001; i++){
        for(int j = 0; j < 10; j++){
            for(int k = 0; k < 10; k++){
                table[i][j][k] = Integer.MAX_VALUE;
            }
        }
    }
    //1756
    table[0][4][6] = 0;
    
    table[1][1][6] = Math.min(table[0][4][6] + cost[4][1], table[1][1][6]);
    table[1][4][1] = Math.min(table[0][4][6] + cost[6][1], table[1][4][1]);
    System.out.println(" ");
    table[2][1][7] = Math.min(table[1][1][6] + cost[6][7], table[2][1][7]);
    table[2][7][6] = Math.min(table[1][1][6] + cost[1][7], table[2][7][6]);
    table[2][4][7] = Math.min(table[1][4][1] + cost[1][7], table[2][4][7]);
    table[2][7][1] = Math.min(table[1][4][1] + cost[4][7], table[2][7][1]);
    System.out.println(" ");
    table[3][1][5] = Math.min(table[2][1][7] + cost[7][5], table[3][1][5]);
    table[3][5][7] = Math.min(table[2][1][7] + cost[1][5], table[3][5][7]);  // 중복1
    table[3][7][5] = Math.min(table[2][7][6] + cost[6][5], table[3][7][5]);  // 중복2
    table[3][5][6] = Math.min(table[2][7][6] + cost[7][5], table[3][5][6]);
    table[3][5][7] = Math.min(table[2][4][7] + cost[4][5], table[3][5][7]);  // 중복1
    table[3][4][5] = Math.min(table[2][4][7] + cost[7][5], table[3][4][5]);
    table[3][7][5] = Math.min(table[2][7][1] + cost[1][5], table[3][7][5]);  // 중복2
    table[3][5][1] = Math.min(table[2][7][1] + cost[7][5], table[3][5][1]);
    
    System.out.println(" ");
    ArrayList<Integer> getMin = new ArrayList<>();
    getMin.add(table[4][1][6] = Math.min(table[3][1][5] + cost[5][6], table[4][1][6]));
    getMin.add(table[4][6][5] = Math.min(table[3][1][5] + cost[1][6], table[4][6][5]));  // 중복1
    getMin.add(table[4][5][6] = Math.min(table[3][5][7] + cost[7][6], table[4][5][6]));  // 중복2
    getMin.add(table[4][6][7] = Math.min(table[3][5][7] + cost[5][6], table[4][6][7]));
    getMin.add(table[4][7][6] = Math.min(table[3][7][5] + cost[5][6], table[4][7][6]));
    getMin.add(table[4][6][5] = Math.min(table[3][7][5] + cost[7][6], table[4][6][5]));  // 중복1
    getMin.add(table[4][5][6] = Math.min(table[3][5][6] + cost[6][6], table[4][5][6]));  // 중복2
    getMin.add(table[4][6][6] = Math.min(table[3][5][6] + cost[5][6], table[4][6][6])); // 같은 키에 두 손가락이 다 있어서 탈락.
    getMin.add(table[4][4][6] = Math.min(table[3][4][5] + cost[5][6], table[4][4][6]));
    getMin.add(table[4][6][5] = Math.min(table[3][4][5] + cost[4][6], table[4][6][5]));  // 중복1
    getMin.add(table[4][5][6] = Math.min(table[3][5][1] + cost[1][6], table[4][5][6]));  // 중복2
    getMin.add(table[4][6][1] = Math.min(table[3][5][1] + cost[5][6], table[4][6][1]));
    
    Collections.sort(getMin);
    
    System.out.println(getMin.get(0));
    
}
public int solution(String numbers) {
    int[][][] table = new int[100001][10][10];
    //1756
    table[0][4][6] = 0;
    
    table[1][1][6] = Math.min(table[0][4][6] + cost[4][1], table[1][1][6]);
    table[1][4][1] = Math.min(table[0][4][6] + cost[6][1], table[1][4][1]);
    
    table[2][1][7] = Math.min(table[1][1][6] + cost[6][7], table[2][1][7]);
    table[2][7][6] = Math.min(table[1][1][6] + cost[1][7], table[2][7][6]);
    table[2][4][7] = Math.min(table[1][4][1] + cost[1][7], table[1][1][6]);
    table[2][7][1] = Math.min(table[1][4][1] + cost[4][7], table[2][7][1]);
    
    table[3][1][5] = Math.min(table[2][1][7] + cost[7][5], table[3][1][5]);
    table[3][5][7] = Math.min(table[2][1][7] + cost[1][5], table[3][5][7]); // 중복1
    table[3][7][5] = Math.min(table[2][7][6] + cost[6][5], table[3][7][5]); // 중복2
    table[3][5][6] = Math.min(table[2][7][6] + cost[7][5], table[3][5][6]);
    table[3][5][7] = Math.min(table[2][4][7] + cost[4][5], table[3][5][7]); // 중복1
    table[3][4][5] = Math.min(table[2][4][7] + cost[7][5], table[3][4][5]);
    table[3][7][5] = Math.min(table[2][7][1] + cost[1][5], table[3][7][5]); // 중복2
    table[3][5][1] = Math.min(table[2][7][1] + cost[7][5], table[3][5][1]);
    
    
    ArrayList<Integer> getMin = new ArrayList<>();
    getMin.add(table[4][1][6] = Math.min(table[3][1][5] + cost[5][6], table[4][1][6]));
    getMin.add(table[4][6][5] = Math.min(table[3][1][5] + cost[1][6], table[4][6][5])); // 중복1
    getMin.add(table[4][5][6] = Math.min(table[3][5][7] + cost[7][6], table[4][5][6])); // 중복2
    getMin.add(table[4][6][7] = Math.min(table[3][5][7] + cost[5][6], table[4][6][7]));
    getMin.add(table[4][7][6] = Math.min(table[3][7][5] + cost[5][6], table[4][7][6]));
    getMin.add(table[4][6][5] = Math.min(table[3][7][5] + cost[7][6], table[4][6][5])); // 중복1
    getMin.add(table[4][5][6] = Math.min(table[3][5][6] + cost[6][6], table[4][5][6])); // 중복2
    getMin.add(table[4][6][6] = Math.min(table[3][5][6] + cost[5][6], table[4][6][6])); // 같은 키에 두 손가락이 다 있어서 탈락.
    getMin.add(table[4][4][6] = Math.min(table[3][4][5] + cost[5][6], table[4][4][6]));
    getMin.add(table[4][6][5] = Math.min(table[3][4][5] + cost[4][6], table[4][6][5])); // 중복1
    getMin.add(table[4][5][6] = Math.min(table[3][5][1] + cost[1][6], table[4][5][6])); // 중복2
    getMin.add(table[4][6][1] = Math.min(table[3][5][1] + cost[5][6], table[4][6][1]));
    
    Collections.sort(getMin);
    
    int answer = getMin.get(0);
    
    return answer;
}

public void getNext(int turn, int left, int right, int[][][] table, int nextNum){
    
    
    table[1][4][1] = 0;
    table[1][1][6] = 0;
}
}