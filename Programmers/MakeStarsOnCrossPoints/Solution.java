package CodingTestStudy.MakeStarsOnCrossPoints;

import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // n개의 직선이 주어질 때, 교점의 개수는 Sigma(1, n-1) = (n-1)n/2
        // n은 1000 이하이므로 교점의 개수는 1,000,000 이하. 전체 다 구하면 됨.
        // 두 선의 교점을 구하는 방법만 찾으면 될 듯.
        List<long[]> crossPoints = new ArrayList<>();

        for(int i=0; i<line.length-1; i++){
            for (int j=i+1; j<line.length; j++){
                long[] crossPoint = findCrossPoint(line[i], line[j]);
                if(crossPoint != null){
                    crossPoints.add(crossPoint);
                }
            }
        }

        // crossPoints.add(new int[]{1, 2});
        // crossPoints.add(new int[]{2, 3});
        // if(crossPoints.isEmpty()) 문제 조건 상 이 경우는 생기지 않음
        long minX = crossPoints.get(0)[0];
        long maxX = minX;
        long minY = crossPoints.get(0)[1];
        long maxY = minY;

        for(int i=1; i<crossPoints.size(); i++){
            long[] point = crossPoints.get(i);
            if(point[0] < minX) minX = point[0];
            else if(point[0] > maxX) maxX = point[0];
            if(point[1] < minY) minY = point[1];
            if(point[1] > maxY) maxY = point[1];
        }

        int w = (int)(maxX - minX + 1);
        int h = (int)(maxY - minY + 1);

        char[][] field = new char[h][w];
        for(int i=0; i<h; i++){for(int j=0; j<w; j++){ field[i][j] = '.';}}

        for(long[] crossPoint: crossPoints){
            field[(int)(crossPoint[1]-minY)][(int)(crossPoint[0]-minX)] = '*';
        }

        String[] answer = new String[h];
        for(int i=0; i<h; i++){
            String str = new String(field[i]);
            answer[h-i-1] = str;
        }


        return answer;
    }

    long[] findCrossPoint(int[] line1, int[] line2){
        // 평행한 경우 -> 교점 없음 (null 반환)
        // 네 계수 중 0이 있는 경우
        // x의 계수 또는 y의 계수가 같은 경우
        // 한쪽의 계수가 다른쪽의 배수인 경우
        // 위 경우 모두 아닌 경우
        long div = line1[0] * line2[1] - line1[1] * line2[0];

        if(div==0){
            // 평행
            return null;
        }
        long X = ((long)line1[1])*line2[2] - ((long)line2[1])*line1[2];
        long Y = ((long)line2[0])*line1[2] - ((long)line1[0])*line2[2];
        if(X % div != 0 || Y % div !=0) return null;

        return new long[]{X/div, Y/div};
    }
}