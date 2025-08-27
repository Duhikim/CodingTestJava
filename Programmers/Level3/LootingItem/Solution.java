package CodingTestStudy.Level3.LootingItem;

import java.util.*;
import java.lang.Math;
class Solution {
    int minX, minY, maxX, maxY;
    int[][] rectangle;
    int characterX, characterY, itemX, itemY;
    Map<String, Boolean> visit;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        minX = Integer.MAX_VALUE; minY = Integer.MAX_VALUE; maxX = 0; maxY = 0;
        this.rectangle = rectangle;
        characterX *= 2; characterY *= 2; itemX *= 2; itemY*=2;
        this.characterX = characterX; this.characterY=characterY; this.itemX = itemX; this.itemY = itemY;

        for(int[] rect: rectangle){
            rect[0] *= 2;
            rect[1] *= 2;
            rect[2] *= 2;
            rect[3] *= 2;
            if(minX > rect[0]) minX = rect[0];
            if(minY > rect[1]) minY = rect[1];
            if(maxX < rect[2]) maxX = rect[2];
            if(maxY < rect[3]) maxY = rect[3];
        }

        visit = new HashMap<>();

        for(int i=minX; i<=maxX; i++){
            for(int j=minY; j<=maxY; j++){
                if(where(i, j)==0) {
                    String pos = i + "," + j;
                    visit.put(pos, false);
                }
            }
        }

        visit.put(characterX+","+characterY, true);
        search(characterX, characterY, 0);


        return answer/2;
    }

    public void search(int sx, int sy, int steps){
        System.out.println("(" + sx + " , " + sy + ") 진입. 스텝 : " + steps);
        if(sx==itemX && sy==itemY){
            answer = Math.min(answer, steps);
            return;
        }

        for(int i=0; i<4; i++){
            int nx = sx+dx[i];
            int ny = sy+dy[i];
            String key = nx + "," + ny;
            if(!visit.containsKey(key)) continue;
            if(visit.get(key)) continue;
            visit.put(key, true);
            search(nx, ny, steps+1);
            visit.put(key, false);
        }

    }

    // 내부 -1, 선위 0, 외부 1
    public int where(int x, int y){
        int result = 1;
        for(int[] rect: rectangle){
            if(rect[0] < x && x < rect[2] && rect[1] < y && y < rect[3]) return -1;
            if((x==rect[0] && rect[1] < y && y < rect[3])
                    || (x==rect[2] && rect[1] <= y && y <= rect[3])
                    || (y==rect[1] && rect[0] <= x && x <= rect[2])
                    || (y==rect[3] && rect[0] <= x && x <= rect[2]) ){
                result = 0;
            }
        }
        return result;
    }
}