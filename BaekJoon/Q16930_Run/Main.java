package BaekJoon.Q16930_Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
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
        ArrayDeque<Point> QueFromFront = new ArrayDeque<>();
        ArrayDeque<Point> QueFromBack = new ArrayDeque<>();
    
        int cost = 0;
        Matrix[sX][sY].visitedFromFront = true;
        Matrix[sX][sY].cost = cost;
        QueFromFront.add(Matrix[sX][sY]);
        int sizeOfQueFromFront = 1;
        
        Matrix[eX][eY].visitedFromBack = true;
        Matrix[eX][eY].cost = cost;
        QueFromBack.add(Matrix[eX][eY]);
        int sizeOfQueFromBack = 1;
        
        int[] dx = {-1, 1, 0, 0}; // UP DOWN LEFT RIGHT
        int[] dy = {0, 0, -1, 1};
        
        do {
            cost++;
           
            int size = sizeOfQueFromFront;
            sizeOfQueFromFront = 0;
            for (; size >0; size--) {
                int tempX = QueFromFront.peekFirst().x;
                int tempY = QueFromFront.pollFirst().y;
                
                for(int dir=0; dir<4; dir++) { //Up, Down, Left, Right 순서로 탐색.
                    for (int i = 1; i <= K; i++) {  // 한 칸 ~ K 칸까지 탐색
                        int x = tempX + dx[dir]*i;
                        int y = tempY + dy[dir]*i;
                        if (x < 0 || y < 0 || x >= Matrix.length || y >= Matrix[0].length
                                || !Matrix[x][y].isWay) break; // 벽을 만나거나 범위에서 벗어나면 그 방향 탐색 종료.
                        if (Matrix[x][y].visitedFromFront) { continue; } // 이미 방문한 노드면 패스(탐색은 계속해야한다)
                        if (Matrix[x][y].visitedFromBack) {
                            System.out.println(Matrix[x][y].cost + cost); // 종점. (출력)
                            return;
                        }
                        Matrix[x][y].cost = cost;
                        Matrix[x][y].visitedFromFront = true;
                        QueFromFront.addLast(Matrix[x][y]);
                        sizeOfQueFromFront++;
                    }
                }
            }
            
            size = sizeOfQueFromBack;
            sizeOfQueFromBack = 0;
            for(; size > 0; size--) {
                int tempX = QueFromBack.peekFirst().x;
                int tempY = QueFromBack.pollFirst().y;
                
                for(int dir=0; dir<4; dir++) { //Up, Down, Left, Right 순서로 탐색.
                    for (int i = 1; i <= K; i++) {  // 한 칸 ~ K 칸까지 탐색
                        int x = tempX + dx[dir]*i;
                        int y = tempY + dy[dir]*i;
                        if (x < 0 || y < 0 || x >= Matrix.length || y >= Matrix[0].length
                                || !Matrix[x][y].isWay) break; // 벽을 만나거나 범위에서 벗어나면 그 방향 탐색 종료.
                        if (Matrix[x][y].visitedFromBack) { continue; } // 이미 방문한 노드면 패스(탐색은 계속해야한다)
                        if (Matrix[x][y].visitedFromFront) {
                            System.out.println(Matrix[x][y].cost + cost); // 종점. (출력)
                            return;
                        }
                        Matrix[x][y].cost = cost;
                        Matrix[x][y].visitedFromBack = true;
                        QueFromBack.addLast(Matrix[x][y]);
                        sizeOfQueFromBack++;
                    }
                }
            }
        }while(sizeOfQueFromFront>0 && sizeOfQueFromBack>0);
        System.out.println("-1");
    }
}