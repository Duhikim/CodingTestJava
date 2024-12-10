package BaekJoon.Q16930_Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_ver3 {
public static class Point {
        int x;
        int y;
        int cost;
        boolean isWay;
        boolean visitedFromFront;
        boolean visitedFromBack;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
            this.cost = -1;
            this.isWay = false;
            this.visitedFromFront = false;
            this.visitedFromBack = false;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        Point[][] Matrix = new Point[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                Matrix[i][j] = new Point(i, j);
                if(str.charAt(j)=='.') {
                    Matrix[i][j].isWay = true;
                }
            }
        }
        input = br.readLine().split(" ");
        int sX = Integer.parseInt(input[0]), sY=Integer.parseInt(input[1]),
                eX=Integer.parseInt(input[2]), eY = Integer.parseInt(input[3]);

        init(Matrix, sX-1, sY-1, K, eX-1, eY-1);
        
    }
    public static void init(Point[][] Matrix, int sX, int sY, int K, int eX, int eY){
        ArrayDeque<ArrayList<Integer>> curQueFromFront = new ArrayDeque<>();
        ArrayDeque<ArrayList<Integer>> nextQueFromFront;
        ArrayDeque<ArrayList<Integer>> curQueFromBack = new ArrayDeque<>();
        ArrayDeque<ArrayList<Integer>> nextQueFromBack;
    
        int cost = 0;
        Matrix[sX][sY].visitedFromFront = true;
        Matrix[sX][sY].cost = cost;
        curQueFromFront.add(new ArrayList<>(Arrays.asList(sX,sY)));
        
        Matrix[eX][eY].visitedFromBack = true;
        Matrix[eX][eY].cost = cost;
        curQueFromBack.add(new ArrayList<>(Arrays.asList(eX,eY)));
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int size1 = 10, size2 = 10;
        
        do {
            cost++;
            nextQueFromFront = new ArrayDeque<>(size1);
            nextQueFromBack = new ArrayDeque<>(size2);
            
            while (!curQueFromFront.isEmpty()) {
                int tempX = curQueFromFront.peekFirst().get(0);
                int tempY = curQueFromFront.pollFirst().get(1);
                
                for(int dir=0; dir<4; dir++) { //Up, Down, Left, Right 순서로 탐색.
                    for (int i = 1; i <= K; i++) {  // 한 칸 ~ K 칸까지 탐색
                        int x = tempX + dx[dir]*i;
                        int y = tempY + dy[dir]*i;
                        if (x < 0 || y < 0 || x >= Matrix.length || y >= Matrix[0].length
                                || !Matrix[x][y].isWay) break; // 벽을 만나거나 범위에서 벗어나면 그 방향 탐색 종료.
                        if (Matrix[x][y].visitedFromFront) { continue; } // 이미 방문한 노드면 패스(탐색은 계속해야한다)
                        if(Matrix[x][y].visitedFromBack) {
                            System.out.println(Matrix[x][y].cost + cost); // 출력물
                            return;
                        }
                        Matrix[x][y].cost = cost;
                        Matrix[x][y].visitedFromFront = true;
                        ArrayList<Integer> coord =
                                new ArrayList<>(Arrays.asList(x, y));
                        nextQueFromFront.add(coord);
                    }
                }
            }
            
            while(!curQueFromBack.isEmpty()) {
                int tempX = curQueFromBack.peekFirst().get(0);
                int tempY = curQueFromBack.pollFirst().get(1);
                
                for(int dir=0; dir<4; dir++) { //Up, Down, Left, Right 순서로 탐색.
                    for (int i = 1; i <= K; i++) {  // 한 칸 ~ K 칸까지 탐색
                        int x = tempX + dx[dir]*i;
                        int y = tempY + dy[dir]*i;
                        if (x < 0 || y < 0 || x >= Matrix.length || y >= Matrix[0].length
                                || !Matrix[x][y].isWay) break; // 벽을 만나거나 범위에서 벗어나면 그 방향 탐색 종료.
                        if (Matrix[x][y].visitedFromBack) { continue; } // 이미 방문한 노드면 패스(탐색은 계속해야한다)
                        if(Matrix[x][y].visitedFromFront) {
                            System.out.println(Matrix[x][y].cost + cost); // 출력물
                            return;
                        }
                        Matrix[x][y].cost = cost;
                        Matrix[x][y].visitedFromBack = true;
                        ArrayList<Integer> coord =
                                new ArrayList<>(Arrays.asList(x, y));
                        nextQueFromBack.add(coord);
                    }
                }
            }
            
            curQueFromFront = nextQueFromFront;
            curQueFromBack = nextQueFromBack;
            size1 = curQueFromFront.size(); size2 = nextQueFromBack.size();
            
        }while(!nextQueFromBack.isEmpty() && !nextQueFromFront.isEmpty());
        System.out.println("-1");
    }
}
