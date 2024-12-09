package BaekJoon.Q16930_Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static class Point {
        int x;
        int y;
        int cost;
        boolean isWay;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
            this.cost = -1;
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
                if(str.charAt(j)=='#'){
                    Matrix[i][j].isWay = false;
                }
                else Matrix[i][j].isWay = true;
            }
        }
        input = br.readLine().split(" ");
        int sX = Integer.parseInt(input[0]), sY=Integer.parseInt(input[1]),
                eX=Integer.parseInt(input[2]), eY = Integer.parseInt(input[3]);





    }
    public static void init(Point[][] Matrix, int sX, int sY, int cost, int K){
        Matrix[sX][sY].cost = cost;

        //Up
        for(int i=1; i<=K; i++){
            int x = sX - i;
            int y = sY;
            if(x<0 || !Matrix[x][y].isWay) break;
            Matrix[x][y].cost = cost + 1;
        }
        //Down
        for(int i=1; i<=K; i++){
            int x = sX + i;
            int y = sY;
            if(x>=Matrix.length || !Matrix[x][y].isWay) break;
            Matrix[x][y].cost = cost + 1;
        }
        //Left
        for(int i=1; i<=K; i++){
            int x = sX;
            int y = sY - i;
            if(y<0 || !Matrix[x][y].isWay) break;
            Matrix[x][y].cost = cost + 1;
        }
        //Right
        for(int i=1; i<=K; i++){
            int x = sX;
            int y = sY + i;
            if(y>=Matrix[0].length || !Matrix[x][y].isWay) break;
            Matrix[x][y].cost = cost + 1;
        }

    }
}
