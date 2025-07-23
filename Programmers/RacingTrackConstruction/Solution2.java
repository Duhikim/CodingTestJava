package CodingTestStudy.RacingTrackConstruction;

import java.util.*;

public class Solution2 {

    static final int STRAIGHT_COST = 100;
    static final int CORNER_COST = 500;

    // 상, 하, 좌, 우 방향
    static final int[][] DIRECTIONS = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    static class State {
        int row, col, dir, cost;
        State(int row, int col, int dir, int cost) {
            this.row = row;
            this.col = col;
            this.dir = dir; // -1: 초기 상태
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        int N = board.length;
        // visited[y][x][dir] = 최소 비용
        int[][][] visited = new int[N][N][4];
        for (int[][] layer : visited) {
            for (int[] row : layer) Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        pq.offer(new State(0, 0, -1, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int r = current.row, c = current.col, dir = current.dir, cost = current.cost;

            if (r == N-1 && c == N-1) return cost;

            for (int newDir = 0; newDir < 4; newDir++) {
                int nr = r + DIRECTIONS[newDir][0];
                int nc = c + DIRECTIONS[newDir][1];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 1) continue;

                int newCost = cost + STRAIGHT_COST;
                if (dir != -1 && dir != newDir) newCost += CORNER_COST;

                if (visited[nr][nc][newDir] > newCost) {
                    visited[nr][nc][newDir] = newCost;
                    pq.offer(new State(nr, nc, newDir, newCost));
                }
            }
        }

        // 이론상 절대 도달하지 않음 (문제에서 보장됨)
        return -1;
    }
}
