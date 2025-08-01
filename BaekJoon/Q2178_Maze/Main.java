package BaekJoon.Q2178_Maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[][] field;
    static boolean[][] visit;
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // UP RIGHT DOWN LEFT

    public static void main(String[] args) throws IOException {

        String input = br.readLine();
        String[] str = input.split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        field = new char[N][M];
        visit = new boolean[N][M];
        getInput();

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 1}); // x, y, step
        visit[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1], step = now[2];

            if (x == N - 1 && y == M - 1) {
                System.out.println(step);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + directions[d][0];
                int ny = y + directions[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (field[nx][ny] == '0' || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                q.add(new int[]{nx, ny, step + 1});
            }
        }
    }

    static public void getInput() throws IOException {
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                field[i][j] = input.charAt(j);
            }
        }
    }


}
