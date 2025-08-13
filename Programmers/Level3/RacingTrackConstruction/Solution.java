package CodingTestStudy.Level3.RacingTrackConstruction;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    int[][] board;
    Node[][] nodes;
    int h, w;
    int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 하, 상, 우, 좌

    class Node{
        int straight;
        int curve;
        int cost;
        int dir; // 0 or 2. 가로로왔다면 0, 세로로왔다면 2

        public Node(int straight, int curve, int dir){
            this.straight = straight;
            this.curve = curve;
            this.cost = straight+5*curve;
            this.dir = dir;
        }
        public void update(int straight, int curve, int dir){
            if(straight+5*curve < this.cost){
                this.straight = straight;
                this.curve = curve;
                this.cost = straight+5*curve;
                this.dir = dir;
            }
        }
    }

    public int solution(int[][] board) {
        this.board = board;
        h = board.length;
        w = board[0].length;

        nodes = new Node[h][w];
        Queue<int[]> queue = new LinkedList<>();
        nodes[0][0] = new Node(0, 0, 0);

        for(int dir=0; dir<4; dir++){
            int len = 1;
            while(true) {
                int newR = direction[dir][0] * len;
                int newC = direction[dir][1] * len;
                if (newR < 0 || newC < 0 || newR >= h || newC >= w || board[newR][newC] == 1) break;
                nodes[newR][newC] = new Node(len, 0, (dir<2)?2:0);
                queue.add(new int[]{newR, newC});
                len++;
            }
        }

        while(!queue.isEmpty()){
            int[] coord = queue.poll();
            int priorDir = nodes[coord[0]][coord[1]].dir;

            for(int dir=priorDir; dir<priorDir+2; dir++){
                int len = 1;
                while(true) {
                    int newR = coord[0] + direction[dir][0] * len;
                    int newC = coord[1] + direction[dir][1] * len;
                    if (newR < 0 || newC < 0 || newR >= h || newC >= w || board[newR][newC] == 1) break;

                    int newStraight = nodes[coord[0]][coord[1]].straight + len;
                    int newCurve = nodes[coord[0]][coord[1]].curve + 1;
                    int newCost = newStraight + 5 * newCurve;
                    if (nodes[newR][newC] == null) {
                        nodes[newR][newC] = new Node(newStraight, newCurve, (dir<2)?2:0);
                        queue.add(new int[]{newR, newC});
                    } else if (nodes[newR][newC].cost > newCost) {
                        nodes[newR][newC].update(newStraight, newCurve, (dir<2)?2:0);
                        queue.add(new int[]{newR, newC});
                    }
                    len++;
                }
            }
        }

        return nodes[h-1][w-1].cost * 100;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board;

        board = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        System.out.println(sol.solution(board));
    }
}