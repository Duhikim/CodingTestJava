package CodingTestStudy.Level3.CounselorNumber;

/*
1번 유형에 대해
멘토가 한명일 때의 대기시간 계산 (1)
멘토가 두명일 때의 대기시간 계산 (2) >>> (1) - (2)가 멘토를 두명으로 늘릴 때 얻게되는 기대 이득임
...
멘토가 n-k+1명일 때 대기시간 계산

위 정보를 2번, 3번, ... k번 유형까지 미리 계산을 해놓는다.

그다음에 일단 멘토를 한명씩 배치하고 한명에서 두명, 두명에서 세명 등 멘토를 추가했을 때 이득되는 시간이 가장 큰쪽에 멘토를 배치한다.
*/
import java.util.*;

class Solution_ver4 {
    int k, n;
    int[][] reqs;
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;

        this.k = k;
        this.n = n;
        this.reqs = reqs;

        Deque<Integer>[] waitingTime = getWaitingTimes();

        // 각 유형별로 멘토 한명씩 배치.
        for(int i=1; i<=k; i++){
            answer += waitingTime[i].pollFirst();
        }
        int mentors = n-k;
        while(mentors > 0){
            mentors--;
            int maxBenefit = 0;
            int type = 0;

            for(int i=1; i<=k; i++){
                if(waitingTime[i].isEmpty()) continue;
                int benefit = waitingTime[i].peekFirst();
                if(benefit > maxBenefit){
                    maxBenefit = benefit;
                    type = i;
                }
            }
            if(type==0) break;
            answer -= waitingTime[type].pollFirst();
        }

        return answer;
    }

    public Deque<Integer>[] getWaitingTimes(){
        Deque<Integer>[] result = new Deque[k+1];

        for(int i=1; i<=k; i++){
            result[i] = new ArrayDeque<>();
        }

        for(int i=1; i<=k; i++){

            int waitingTime = getWaitingTime(i, 1);
            // System.out.println(i + " 유형의 멘토가 " + 1 + "명일 때 대기 시간은 " + waitingTime + " 분");
            result[i].add(waitingTime);
            int prev = waitingTime;
            if(waitingTime == 0) continue;

            for(int j=2; j<=n-k+1; j++){
                waitingTime = getWaitingTime(i, j);
                // System.out.println(i + " 유형의 멘토가 " + j + "명일 때 대기 시간은 " + waitingTime + " 분");
                result[i].add(prev - waitingTime);
                prev = waitingTime;
                if(waitingTime == 0) break;
            }
        }

        return result;
    }

    public int getWaitingTime(int type, int mentors){
        int result = 0;
        PriorityQueue<Integer> onCounsel = new PriorityQueue<>((a, b)->a-b);

        for(int[] req: reqs){
            int start = req[0];
            int duration = req[1];
            int tp = req[2];
            if(tp != type) continue;
            if(mentors > 0){
                mentors--;
                onCounsel.add(start+duration);
            } else{
                int prevEnd = onCounsel.poll();
                if(prevEnd <= start){
                    onCounsel.add(start+duration);
                } else{
                    onCounsel.add(prevEnd+duration);
                    result += prevEnd - start;
                }
            }
        }
        return result;
    }
}