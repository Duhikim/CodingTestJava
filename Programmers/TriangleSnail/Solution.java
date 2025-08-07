package CodingTestStudy.TriangleSnail;

import java.util.*;
import java.lang.Math;

class Solution {
    public int[] solution(int n) {
        List<int[]> arrs = new ArrayList<>();
        for(int i=0; i<n; i++){
            arrs.add(new int[i+1]);
        }
        int sR = 0;
        int eR = n-1;
        int cycle = 0;
        int num = 1;
        while(sR <= eR){
            for(int i=sR; i<=eR; i++){
                arrs.get(i)[cycle] = num;
                num++;
            }
            for(int j=cycle+1; j<=n-1-2*cycle; j++){
                arrs.get(eR)[j] = num;
                num++;
            }
            for(int i=eR-1; i>sR; i--){
                arrs.get(i)[i-cycle] = num;
                num++;
            }

            sR+=2;
            eR-=1;
            cycle++;
        }


        int[] answer = new int[n*(n+1)/2];
        int idx = 0;
        for(int[] arr: arrs){
            for(int number: arr){
                answer[idx] = number;
                idx++;
            }
        }
        return answer;

    }
}