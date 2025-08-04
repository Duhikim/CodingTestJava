package CodingTestStudy.SocialDistance;
import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solution{
    String[] place = new String[]{};
    public int[] solution(String[][] places){
        int[] answer = new int[5];

        for(int i=0; i<5; i++) {
            this.place = places[i];
            answer[i] = 1;

            for(int j=0; j<5; j++){
                for (int k=0; k<5; k++){
                    if(place[j].charAt(k) != 'P') continue;
                    if(!isProper(j, k)) {
                        answer[i] = 0;
                        break;
                    }
                }if(answer[i] == 0) break;
            }
        }
        return answer;
    }
    public boolean isProper(int r, int c){
        int[][] directions = new int[][]{{-1, 0},{0, 1},{1, 0},{0, -1}};
        int nr, nc;
        for(int d=0; d<4; d++){
            nr = r+ directions[d][0];
            nc = c+ directions[d][1];
            if(nr<0 || nc<0 || nr>=5 || nc>= 5) continue;
            char ch = place[nr].charAt(nc);
            if(ch == 'P') return false;
            if(ch == 'X') continue;
            if(ch == 'O'){
                for(int dd=0; dd<4; dd++){
                    int nnr = nr + directions[dd][0];
                    int nnc = nc + directions[dd][1];
                    if(nnr<0 || nnc<0 || nnr>=5 || nnc>= 5) continue;
                    if(nnr == r && nnc == c) continue;
                    char ch2 = place[nnr].charAt(nnc);
                    if(ch2 == 'P') return false;
                }
            }
        }
        return true;
    }
}