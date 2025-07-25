package CodingTestStudy.GoormQuiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution4 {

    static int answer = -1;
    static int N, M;
    static int[][] dir = new int[][]
            {{-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}};
    static char[][] field;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String size = br.readLine();
        String[] sz = size.split(" ");
        N = Integer.parseInt(sz[0]);
        M = Integer.parseInt(sz[1]);
        field = new char[N][M];
        visit = new boolean[N][M];
        int[] start = new int[2];

        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                field[i][j] = input.charAt(j);
                if(field[i][j] == 'S'){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        search(start[0], start[1], 0);

        System.out.println(answer);
    }

    public static void search(int startR, int startC, int cost){
        if(field[startR][startC] == 'E'){
            answer = (answer==-1 || cost < answer)? cost: answer;
            return;
        }

        visit[startR][startC] = true;

        for(int i=0; i<8; i++){
            int newR = startR + dir[i][0];
            int newC = startC + dir[i][1];
            int newCost = cost+1;
            if(newR < 0 || newR >= N || newC < 0 || newC >= M
                    || visit[newR][newC] || field[newR][newC]=='#') continue;
            // 각 좌표는 숫자, 점, #, E 네가지 중 하나임.
            // 숫자면 그방향으로 숫자가 안나올때까지 계속가면서 숫자를 더할거임.
            // 만약 벽을만나거나 #를 만나면 다른방향으로 가야함
            while(field[newR][newC] >= '1' && field[newR][newC] <= '9' ){
                newCost += (int)(field[newR][newC] - '0');
                newR += dir[i][0];
                newC += dir[i][1];
                if(newR < 0 || newR >= N || newC < 0 || newC >= M) break;
            }

            // 그 외에는 재귀호출
            if(newR >= 0 && newR < N && newC >= 0 && newC < M
                    && !visit[newR][newC] && field[newR][newC]!='#'){
                search(newR, newC, newCost);
                visit[newR][newC] = false;
            }

        }

    }
}
