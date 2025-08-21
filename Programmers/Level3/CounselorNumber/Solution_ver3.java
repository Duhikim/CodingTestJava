package CodingTestStudy.Level3.CounselorNumber;

/*
최대값 기준 상담사 20명 중 5명은 유형 1~k를 먹고들어가고 나머지 15명은 5개의 유형 중 아무거나 선택해도 된다.
원래 15^5지만, 실제로는 그것보다 적음. 왜냐면 상담사는 id가 없으니 머릿수만 중요함.
5가지 타입을 중복을 허용해 15번 뽑으면 됨.
5H15 = 19C15 = 19C4 = 19 6 17 2  < 20 * 20 * 10 = 4000

약 4000개의 조합을 만들어서 300개의 reqs를 처리하여 최소값을 반환.
1,200,000

비트마스크를 이용해서 19개의 비트를 만듦. int는 32비트니까 오버플로 없음.
2^19 < 2^20 = (2^10)^2 ~= 1,000,000
백만의 숫자를 싹 훑어서 1이 4개(혹은 15개)인 숫자를 확인하여 그걸로 상담사 조합을 생성
*/

import java.lang.Math;
import java.util.*;

class Solution_ver3 {
    int k, n;
    int[][] reqs;
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;

        this.k = k;
        this.n = n;
        this.reqs = reqs;

        // (k)H(n-k) = (n-1)C(n-k) = (n-1)C(k-1)
        // 비트마스크 크기는 n-1이 되어야 한다.

        int fullmask = (1<<(n-1)) -1;

        for(int i=0; i<=fullmask; i++){
            if(Integer.bitCount(i) == (k-1)){
                int[] counseler = getCounseler(i);
                int waitingMins = getWaitingMins(counseler);
                answer = Math.min(answer, waitingMins);
            }
        }
        return answer;
    }

    public int[] getCounseler(int mask){
        int[] result = new int[k+1];

        int idx = 0;
        int prev = -1;
        for(int i=0; i<n-1; i++){
            if((mask & (1<<i)) != 0){
                idx++;
                result[idx] = 1 + i-prev-1; // 처음 1은 기본적으로 타입마다 반드시 한명이 있어야함을 의미
                prev = i;
            }
            if(idx == k-1)break;
        }
        result[k] = 1 + n-2-prev;

        return result;
    }

    public int getWaitingMins(int[] counseler){
        int result = 0;

        PriorityQueue<Integer>[] onCounsel = new PriorityQueue[k+1]; // onCounsel[상담 유형] = 끝나는 시간의 우선순위 큐

        for(int i=1; i<=k; i++){
            onCounsel[i] = new PriorityQueue<>((a, b)->a-b);
        }


        for(int[] req: reqs){
            int start = req[0];
            int duration = req[1];
            int type = req[2];

            if(counseler[type] > 0){
                counseler[type] -= 1;
                onCounsel[type].add(start+duration);
            } else{
                int prevEnd = onCounsel[type].poll();

                if(prevEnd <= start){
                    onCounsel[type].add(start+duration);
                } else{
                    onCounsel[type].add(prevEnd+duration);
                    result += prevEnd - start;
                }
            }
        }

        return result;
    }
}