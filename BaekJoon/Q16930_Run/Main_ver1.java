package BaekJoon.Q16930_Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_ver1 {
    private final int[][] Move = {{-1, 0},    {1, 0},    {0, -1} ,   {0, 1}};
    enum Direction {
        UP , DOWN , LEFT,  RIGHT, NONE
    }
    Direction prevDir = Direction.NONE;
    Direction curDir = Direction.NONE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        char[][] gymFeild = new char[N][M];
        boolean[][] visited = new boolean[N][M]; // false로 초기화
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                gymFeild[i][j] = str.charAt(j);
            }
        }
        input = br.readLine().split(" ");
        int[] sCoord = new int[2];
        int[] eCoord = new int[2];
        for(int i=0; i<2; i++) {
            sCoord[i] = Integer.parseInt(input[i]) - 1;
            eCoord[i] = Integer.parseInt(input[i+2]) - 1;
        }
        visited[sCoord[0]][sCoord[1]]  = true;
        int[] answer = {-1};

        new Main_ver1().move(gymFeild, visited, sCoord, eCoord, K, 0, 0, answer);
        System.out.println(answer[0]);
    }





    public void move(char[][] Feild, boolean[][] visited, int[] current, int[] end, int K, int step, int subAnswer, int[] answer){
        // 방향 바꿀 때 처리
        if(prevDir != Direction.NONE && prevDir != curDir) {
            subAnswer += (step-2)/K + 1;
            step = 1;
        }
        // 도착했을 때 처리
        if(current[0] == end[0] && current[1] == end[1]) {
            subAnswer += (step-1)/K + 1;
            if(answer[0] == -1 || answer[0] > subAnswer) answer[0] = subAnswer;
        }
        // 중간에 글렀을 때 처리
        if(answer[0] != -1 && subAnswer > answer[0]) return;

        // 그 외에는 깊이 우선 탐색을 사용.
        for(int i=0; i<4; i++){
            int[] tempCoord = current.clone();
            tempCoord[0] += Move[i][0];
            tempCoord[1] += Move[i][1];
            if(!canGo(tempCoord, Feild, visited)) continue;
            prevDir = curDir;
            curDir = (i==0)? Direction.UP : (i==1)? Direction.DOWN : (i==2)? Direction.LEFT : Direction.RIGHT;
            visited[tempCoord[0]][tempCoord[1]] = true;
            move(Feild, visited, tempCoord, end, K, step+1, subAnswer, answer);
            visited[tempCoord[0]][tempCoord[1]] = false;
        }


    }
    public static boolean canGo(int[] target, char[][] Feild, boolean[][] visited){
        if(target[0] < 0 || target[0] >= Feild.length || target[1] < 0 || target[1] >= Feild[0].length) return false;
        if(Feild[ target[0] ][ target[1] ] == '#') return false;
        if(visited[ target[0] ][ target[1] ]) return false;
        return true;
    }

}
