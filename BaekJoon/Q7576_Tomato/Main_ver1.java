package BaekJoon.Tomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_ver1 {
public static int[] dx = {-1, 1, 0, 0};
public static int[] dy = {0, 0, -1, 1};

public static class Tomato {
    public static int numOfTomatoes; // 전체 토마토 수
    public static int numOfRipedTomatoes; // 익은 토마토 수
    public static ArrayDeque<Tomato> ripedTomatoes; // 익은 토마토의 배열(데크)

    public int x;
    public int y;
    public int status; // 1: 익음, 0: 익지 않음, -1: 없음
    int day; // 익는 날짜.
    
    
    public Tomato(int x, int y, int stat) {
        numOfTomatoes++;
        
        this.x = x;
        this.y = y;
        this.status = stat;
        this.day = (stat==1)? 0 : -1;
        if(stat==1){
            numOfRipedTomatoes++;
            ripedTomatoes.add(this);
        }
        else if(stat==-1){ numOfTomatoes--; }
    }
}

public static void main(String[] args) throws IOException {
    Tomato.numOfTomatoes = 0;
    Tomato.numOfRipedTomatoes = 0;
    Tomato.ripedTomatoes = new ArrayDeque<>();
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] str = br.readLine().split(" ");
    int M = Integer.parseInt(str[0]);
    int N = Integer.parseInt(str[1]);
    Tomato[][] tomatoes = new Tomato[N][M];
    for (int i = 0; i < N; i++) {
        String[] stat = br.readLine().split(" ");
        for (int j = 0; j < M; j++) {
            tomatoes[i][j] = new Tomato(i, j, Integer.parseInt(stat[j]));
        }
    }
    br.close();
    
    Riping(tomatoes);
}

public static void Riping(Tomato[][] tomatoes){
    int day = 0;
    int size = Tomato.ripedTomatoes.size();
    
    while(size>0) {
        if(Tomato.numOfRipedTomatoes == Tomato.numOfTomatoes) {
            System.out.println(day);
            return;
        }
        day++;
        
        for(int i=0; i<size; i++) {
            Tomato temp = Tomato.ripedTomatoes.pollFirst();
            for (int dir = 0; dir < 4; dir++) {
                int x = temp.x + dx[dir];
                int y = temp.y + dy[dir];
                if (x < 0 || y < 0 || x >= tomatoes.length || y >= tomatoes[0].length
                        || tomatoes[x][y].status == -1) continue;
                
                if (tomatoes[x][y].status == 0) {
                    tomatoes[x][y].status = 1;
                    tomatoes[x][y].day = day;
                    Tomato.numOfRipedTomatoes++;
                    Tomato.ripedTomatoes.add(tomatoes[x][y]);
                }
            }
        }
        size = Tomato.ripedTomatoes.size();
    }
    System.out.println(-1);
}
}
