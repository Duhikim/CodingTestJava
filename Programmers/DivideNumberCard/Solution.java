package CodingTestStudy.DivideNumberCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int solution(int[] arrayA, int[] arrayB) {

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        List<Integer> divisorA = new ArrayList<>();
        divisorA.add(arrayA[0]);
        // O(N), N= 100,000,000 OK
        for(int i=2; i<=arrayA[0]/2; i++){
            if(arrayA[0] % i != 0) continue;
            divisorA.add(i);
            divisorA.add(arrayA[0]/i);
        }
        // divisor의 크기는 최대로 잡아도 200이 넘지 않음
        divisorA.sort((a, b) -> b-a);

        for(int i=divisorA.size()-1; i>=0; i--){
            int div = divisorA.get(i);
            for(int j=1; j<arrayA.length; j++){
                if(arrayA[j] % div != 0){
                    divisorA.remove(i);
                    break;
                }
            }
        }

        // O(200*N), N=500,000, 200*N = 100,000,000 OK
        for(int div: divisorA){
            for(int j=0; j<arrayB.length; j++){
                if(arrayB[j] % div == 0 ) break;
                if(j==arrayB.length-1) return div;
            }
        }
        return 0;
    }
}