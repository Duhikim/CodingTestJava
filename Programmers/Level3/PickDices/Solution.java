package CodingTestStudy.Level3.PickDices;

import java.util.*;

class Solution {
    int fullmask; // 비트마스크
    int n; // 주사위의 수
    int[][] dice;

    public int[] solution(int[][] dice) {

        this.dice = dice;
        this.n = dice.length;
        this.fullmask = (1<<n)-1; // 11111 11111
        int[] answer = new int[n/2];

        for(int i=0; i<n; i++){
            Arrays.sort(dice[i]);
        }

        Map<Integer, Integer> winCountByMask = new HashMap<>();

        for(int i=0; i<=fullmask; i++){
            if(Integer.bitCount(i) != n/2) continue;
            winCountByMask.put(i, getWinCount(i));
            // System.out.println("비트마스크가 " + i + " 일 때 " + winCountByMask.get(i) + "회 승리");
        }

        int maxMask = 0;
        int maxWinCount = 0;

        for(Map.Entry<Integer, Integer> entry: winCountByMask.entrySet()){
            if(entry.getValue() > maxWinCount){
                maxMask = entry.getKey();
                maxWinCount = entry.getValue();
            }
        }
        int idx = 0;
        for(int i=0; i<n; i++){
            if((maxMask & (1<<i)) != 0){
                answer[idx++] = i+1;
            }
        }
        Arrays.sort(answer);

        return answer;
    }

    public int getWinCount(int mask){
        // System.out.println("비트마스크 " + mask);
        // for(int i=0; i<n; i++){
        //     if((mask & 1<<i) != 0) System.out.println((i+1) + "번 주사위 선택");
        // }
        int result = 0;

        int a = mask;
        int b = fullmask - mask;

        int[] scoreA = getScore(a);
        int[] scoreB = getScore(b);
        int[] prefixB = new int[scoreB.length]; // prefixB[X]는 B가 X이하의 점수를 얻는 경우의 수 (scoreB를 누적할거임)

        prefixB[0] = scoreB[0];
        for(int i=1; i<prefixB.length; i++){ // 최대 500
            prefixB[i] = prefixB[i-1] + scoreB[i];
            // System.out.println("scoreB[" + i + "] = " + scoreB[i]);
        }

        for(int i=0; i<scoreA.length; i++){ // 최대 500
            if(scoreA[i] == 0) continue;
            // System.out.println("scoreA[" + i + "] = " + scoreA[i]);
            if(i >= prefixB.length){
                result += power(6, n/2) * scoreA[i];
                // System.out.println("A가 " + i + "점일 때 B 조합과 관계없이 전승");
            }else {
                result += (prefixB[i] - scoreB[i]) * scoreA[i];
                // System.out.println("A가 " + i + "점일 때 이길 수 있는 B 조합은 " + (prefixB[scoreA[i]] - scoreB[scoreA[i]]) + " 개");
            }
        }
        return result;
    }

    public int[] getScore(int mask){
        int max = getMax(mask);
        int[] score = new int[max+1];

        score[0] = 1;

        for(int i=0; i<n; i++){
            if((mask & (1<<i)) == 0) continue;
            int[] next = new int[max+1];
            for(int sum=0; sum<=max; sum++){
                if(score[sum] == 0) continue;
                for(int face: dice[i]){
                    next[sum+face] += score[sum];
                }
            }
            score = next;
        }

        return score;
    }


    public int getMax(int mask){
        int result = 0;
        for(int i=0; i<n; i++){
            if((mask & (1<<i)) != 0){
                result += dice[i][5];
            }
        } return result;
    }

    public int power(int a, int b){
        int n = 1;
        while(b > 0){
            if((b&1)==1) n *= a;
            b >>= 1;
            a *= a;
        }
        return n;
    }
}