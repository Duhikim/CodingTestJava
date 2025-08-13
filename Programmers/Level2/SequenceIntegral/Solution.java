package CodingTestStudy.Level2.SequenceIntegral;

import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        // while 문을 돌면서 그래프 좌표를 구하면서 동시에 해당 구역의 정적분을 구함
        // 정적분 식은 (A+B)/2 (실수타입으로 정의해야함)
        // [a,b] 구역에서 b를 양수로 치환하고 해당 구역에서 정적분 배열을 for문으로 돌면서 answer에 더하면 될듯.
        // 마지막 계산이 2중for문이지만 ranges는 10,000개 이하, integrals도 크지 않기때문에 지장 없음

        List<Double> integrals = new ArrayList<>();

        int n;
        int num = k;
        int prev;

        while(num > 1){
            prev = num;
            if((num & 1) == 0) num >>= 1;
            else num = num*3 + 1;

            Double newIntegral = (num+prev)/2.0;
            integrals.add(newIntegral);
        }
        n = integrals.size();

        double[] answers = new double[ranges.length];

        for(int i = 0; i< ranges.length; i++){
            int start = ranges[i][0], end = n + ranges[i][1];
            if(start > end){
                answers[i] = -1;
                continue;
            } else if(start == end){
                answers[i] = 0;
                continue;
            }

            double answer = 0;
            for(int j= start; j < end; j++){
                answer += integrals.get(j);
            }
            answers[i] = answer;
        }

        return answers;
    }
}