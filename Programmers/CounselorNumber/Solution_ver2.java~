//ver2
package CodingTestStudy.CounselorNumber;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int k=0;
        int n=0;
        int[][] reqs;
        int expected, calculated;
        Solution sol = new Solution();

        k=3;
        n=5;
        reqs = new int[][]{{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        expected= 25;
        calculated = sol.solution(k, n, reqs);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);

        k=2;
        n=3;
        reqs = new int[][]{{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}};
        expected= 90;
        calculated = sol.solution(k, n, reqs);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);
        

        k=1;
        n=1;
        reqs = new int[][]{{0, 100, 1}, {50, 100, 1}, {200, 300, 1}, {500, 100, 1}};
        expected= 50;
        calculated = sol.solution(k, n, reqs);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);

        k=2;
        n=3;
        reqs = new int[][]{{0,100,1},{5,100,2},{10,100,1},{15,30,2},{20,100,1},{45,10,2},{60,5,2}};
        expected= 270;
        calculated = sol.solution(k, n, reqs);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);



    }
    public int solution(int k, int n, int[][] reqs) {
        /*************
         상담의 유형 수 k 는 1~5
         멘토수 n은 k 이상 20이하.
         상담 요청 배열 reqs는 최대 300개.
         멘토들이 상담 유형을 나눠갖는 경우의 수를 구해보자.
         멘토들은 익명이고 유형별로 반드시 한명씩은 들어가야하므로,
         최대의 경우 (20명, 5가지) 5명이 각자 하나씩 맡고 나머지 15명은 자유롭게 고를 수 있다.(중복조합)
         5H15 = 19C15 = 19C4 = 19*18*17*16 / 4*3*2 = 3876. 대충 4000번이라 치자.
         300개의 상담을 처리하는 경우의 수는 1,200,000 번. 적다. 브루트포스 ㄱㄱ
         *************/
        int[] answer = {0};

        // 1. 먼저 멘토 조합을 생성하는 함수를 만든다.
        //      멘토 조합은 하나씩 뱉어내면서 요청처리 함수를 불러야 함. 멘토조합은 서칭을 찾자.
        // 2. 그 조합으로 요청 처리 하면서 기다린 시간 반환.
        // 3. answr가 0인경우 덮어쓰고 그 외에는 끝까지갔는데 적은경우 덮어쓰고 중간에 넘어가면 패스.
        // 유형은 1~5이므로 멘토 배열은 헷갈리지 않게 크기 6으로 잡아놓고 mentor[1]~mentor[5]로 쓰자.
        // mentor[0]은 안씀.
        int[] mentor = new int[6]; // mentor[0]은 빈칸, 1~5까지는 유형별 멘토수가 저장된다. k보다 큰 값은 0으로 저장. 초기에는 전부 0으로 초기화. 하지만 최초에 한개씩은 넣어줘야 함.
        for(int i=1; i<=k; i++){
            mentor[i] = 1;
        }
        counselInit(answer, mentor, k, n-k, reqs, 1);
        return answer[0];
    }

    public void counselInit(int[] answer, int[] mentor, int k, int n, int[][]reqs, int start){
        if(n==0){ // 재귀함수의 종점. 여기서 멘토 조합이 만들어 지고 handleRequest를 불러서 기다린 시간을 계산할거임.
            handleRequest(reqs, mentor, answer);
            return;
        }
        for(int i=start; i<=k; i++){
            mentor[i] += 1;
            counselInit(answer, mentor, k, n-1, reqs, i);
            mentor[i] -= 1;
        }
    }

    public void handleRequest(int[][] reqs, int[] mentor_original, int[] answer) {
        // 상담 중인 건을 HashMap 타입으로 저장한다.
        // 질문 유형을 Key, 상담이 끝나는 시간을 Value로 저장. 해당 유형의 상담이 여러개인 경우 Array를 사용하든 String으로 저장해서 콤마(,)로 구분하든 한다.
        // 상담 요청이 들어 왔을 때, 남은 상담원이 있을때는 상담원-- 후 Map에 추가.
        // 상담원이 없는 경우, Key값으로 Map에 접근. 상담이 가장 일찍 끝나는 시간과(A라고함) 비교한다.
        // 만약, 현재 시간이 A보다 클 경우, A시간은 지워버리고 현재 요청한 사람은 대기없이 바로 상담을 할 수 있다.
        // 만약, 현재 시간이 A보다 작은 경우, A-현재시간 이라는 대기시간이 발생한다. A시간은 지워버리고 현재 요청한 사람이 대기 이후 상담이 끝나는 시간을 저장.
        int answerCandidate = 0;

        HashMap<Integer, TreeMap<Integer, Integer>> mentoring = new HashMap<>(); // Key: 유형, Value: 상담이 끝나는 시간의 배열.
        int[] mentor = mentor_original.clone();

        TreeMap<Integer, Integer>[] endTimeQue = new TreeMap[6]; // 트리맵의 어레이. 각 유형별 트리맵을 의미한다.
        for(int i=0; i<6; i++){
            endTimeQue[i] = new TreeMap<>(); // 유형별(1~5)로 빈 TreeMap을 넣어줌. Key: 상담이 끝나는 시간, Value: 그 시간에 끝나는 상담의 개수. (기본은 1)
        }

        for(int[] req:reqs){ // 각 요청문을 돌면서 처리.
            int startTime = req[0]; //시작 시간
            int period = req[1]; // 걸리는 시간
            int type = req[2]; // 유형
            if(mentor[type]>0){ // 만약 필요한 멘토가 있다면
                mentor[type]--; // 멘토수를 줄이고
                int endTime = startTime + period; // 상담이 끝나는 시간 구하기
                endTimeQue[type].put(endTime, endTimeQue[type].getOrDefault(endTime, 0)+1); // 상담이 끝나는 시간을 트리맵에 추가.
            }
            else{ // 액면 멘토가 없다면
                Map.Entry<Integer, Integer> entry = endTimeQue[type].firstEntry(); // 제일 처음으로 끝나는 상담 건을 확인.
                // 1. getValue값을 하나 낮춰준다. (0이면 삭제할 예정)
                // 2. 기다려야 하는 시간을 구한다. 만약 시작 시간이 첫번째 상담 끝나는 시간보다 크거나 같으면 대기시간은 0이다.
                // 3. 새로운 endTime을 endTimeQue에 추가한다. endTime은 startTime+waitingTime+period.
                endTimeQue[type].put(entry.getKey(), entry.getValue()-1); // 1. getValue값을 하나 낮춰준다.
                if(endTimeQue[type].firstEntry().getValue() == 0){ endTimeQue[type].pollFirstEntry();} // 1. Value값이 0이면 제거.
                int waitingTime = entry.getKey() - startTime; // 2. 기다려야 하는 시간을 구하기
                if(waitingTime<0) waitingTime = 0; // 2. 만약 시작 시간이 첫번쨰 상담 끝나는 시간보다 크거나 같은 경우, 대기시간은 0이다.
                int endTime = startTime + waitingTime + period; // 3. 새로운 endTime을 구하기
                endTimeQue[type].put(endTime, endTimeQue[type].getOrDefault(endTime, 0)+1); // 3. 새로운 endTime을 endTimeQue에 추가.
                answerCandidate += waitingTime;
                if(answer[0]!=0 && answerCandidate>answer[0]) return; // 중간에 answer[0]보다 커지면 중단.
            }
        } if(answer[0]==0 || answer[0] > answerCandidate) answer[0] = answerCandidate;
    }
}