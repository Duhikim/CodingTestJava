package BaekJoon.Tomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_ver2 {

public static ArrayDeque<Coordinate> curDeque;
public static int numOfTomatoes;
public static int numOfRipedTomatoes;


public static class Coordinate {
    int x;
    int y;
  
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public static void main(String[] args) throws IOException {
    curDeque = new ArrayDeque<>();
    numOfTomatoes = 0;
    numOfRipedTomatoes = 0;
    
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] str = br.readLine().split(" ");
    int M = Integer.parseInt(str[0]);
    int N = Integer.parseInt(str[1]);
    int[][] tomatoes = new int[N][M];
    for (int i = 0; i < N; i++) {
        String[] stat = br.readLine().split(" ");
        for (int j = 0; j < M; j++) {
            tomatoes[i][j] = Integer.parseInt(stat[j]);
            if(tomatoes[i][j] == 1) {
                curDeque.add(new Coordinate(i, j));
                numOfTomatoes++;
                numOfRipedTomatoes++;
            }
            else if(tomatoes[i][j] == 0) numOfTomatoes++;
        }
    }
    br.close();
    
    Riping(tomatoes);
}

public static void Riping(int[][] tomatoes){
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    int day = 0;
    int size = curDeque.size();
    
    while(true){
        if(numOfRipedTomatoes == numOfTomatoes) {
            System.out.println(day);
            return;
        }
        if(size == 0) break;
        
        day++;
        for(int i=0; i<size; i++) {
            Coordinate coord = curDeque.pollFirst();
            for(int dir=0; dir<4; dir++) {
                int x = coord.x + dx[dir];
                int y = coord.y + dy[dir];
                if(x>=0 && y>=0 && x<tomatoes.length && y<tomatoes[0].length && tomatoes[x][y]==0) {
                    tomatoes[x][y] = 1;
                    curDeque.add(new Coordinate(x, y));
                    numOfRipedTomatoes++;
                }
            }
            
        }
        size = curDeque.size();
    }
    System.out.println(-1);
    
}
}
