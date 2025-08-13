package CodingTestStudy.Level3.TypingNumbers;

import java.util.ArrayDeque;
import java.util.HashMap;

class Solution_ver2 {

class Table{
    int turn;
    int left;
    int right;
    
    public Table(int turn, int left, int right){
        this.turn = turn;
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
    HashMap<Table, Integer> tableMap = new HashMap<>();
    
    int[] numbers_ = numbers.chars()
            .map(c -> c&15)
            .toArray();
    
    int left=4, right=6, turn =0;
    Table table = new Table(turn, left, right);
    tableMap.put(table,0);
    ArrayDeque<Table> q = new ArrayDeque<>();
    q.add(table);
    
    for(turn = 1; turn <= numbers.length(); turn++){
        int num = numbers_[turn-1];
        int size = q.size();
        while(size-- > 0){
            table = q.pollFirst();
            left = table.left;
            right = table.right;
            if(num != left) {
                Table newTable = new Table(turn, left, num);
                int newCost = tableMap.get(table) + cost[right][num];
                if(newCost < tableMap.getOrDefault(newTable, Integer.MAX_VALUE)) {
                    tableMap.put(newTable, tableMap.get(table) + cost[right][num]);
                    q.add(newTable);
                }
            }
            if(num != right) {
                Table newTable = new Table(turn, num, right);
                int newCost = tableMap.get(table) + cost[left][num];
                if(newCost < tableMap.getOrDefault(newTable, Integer.MAX_VALUE)) {
                    tableMap.put(newTable, tableMap.get(table) + cost[left][num]);
                    q.add(newTable);
                }
            }
        }
    }
    int answer = Integer.MAX_VALUE;
    for(Table lastTables: q){
        answer = Math.min(answer, tableMap.get(lastTables));
    }
    
    return answer;
}
}