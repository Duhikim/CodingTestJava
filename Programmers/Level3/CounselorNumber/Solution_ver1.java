package CodingTestStudy.Level3.CounselorNumber;

import java.util.Map;
import java.util.TreeMap;

class Solution_ver1 {
    public static void main(String[] args) {
        int k=0;
        int n=0;
        int[][] reqs;
        int expected, calculated;
        Solution_ver1 sol = new Solution_ver1();

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

    public void handleRequest(int[][] reqs, int[] mentor, int[] answer) {
        //상담을 시작하면 HashMap을 만들어서 (끝나는 시간, 상담 유형)을 저장한다.
        //끝나는 시간이 겹칠수도 있다. 상담 유형을 정수가 아닌 String으로 저장한다.
        //예를들어 50분에 1번유형이 끝난다면 (50, "1")인데, 만약 같은시간에 4번유형도 끝나면 (50,"14")로 저장.
        //나중에 하나씩 갈라쓰면 됨. 5개밖에 안되므로 상관없음.
        //아 자동정렬되게 TreeMap으로 하자.

        int[] mentor_copy = mentor.clone();
        int waitTime = 0;
        TreeMap<Integer, String> mentoring = new TreeMap<>();

        for(int[] req:reqs){
            // 상담 시작 전에 끝난 상담부터 처리하고 그 다음에 상담 시작 처리하면 꼬이지 않을 듯 함.
            int currentTime = req[0];
                //현재 시간보다 Key 값이 작은 것들은 다 빼낸다.
                while(!mentoring.isEmpty() && mentoring.firstKey() <= currentTime){
                    String str = mentoring.pollFirstEntry().getValue();
                    int len = str.length();
                    if(len == 1){
                        int type=Integer.parseInt(str);
                        mentor_copy[type]++;
                    }
                    else{
                        for(int i=0; i<len; i++){
                            int type = str.charAt(i) - '0';
                            mentor_copy[type]++;
                        }
                    }
                }
                // 완료.

            if(mentor_copy[req[2]] > 0){
                // 멘토가 있으므로 상담 처리.
                mentor_copy[req[2]]--;
                // mentor 배열 수정해주고 mentoring 맵에 끝나는 시간 추가함.
                int finTime = req[0]+req[1];
                mentoring.put(finTime, mentoring.getOrDefault(finTime, "") + req[2]);
                // 완료
            }
            else {
                // 멘토가 없으므로 기다려야 함.
                // 여기서 기다리는 시간 올라가고 매 사이클 answer[0]와 비교할 예정.
                // 기다리는 시간만 계산하고나서 바로 mentoring에 집어넣어도 문제 없을듯?
                // 이경우 멘토 수가 음수로 가지만 어차피 나오자마자 다시 들어가니 문제 없음.
                int waiting = - mentor_copy[req[2]];

                for(Map.Entry<Integer, String> entry: mentoring.entrySet()){
                    String str = entry.getValue();
                    int len = str.length();
                    boolean found = false;
                    for(int i=0; i<len; i++){
                        if((int)(str.charAt(i)-'0')==req[2]){
                            if (waiting > 0) {
                                waiting--;
                                continue;
                            }
                            found = true;
                            break;
                        }
                    }
                    if(found) {
                        waitTime += entry.getKey() - currentTime; // 상담시작시간 - 현재 시간
                        int finTime = entry.getKey() + req[1];
                        mentoring.put(finTime, mentoring.getOrDefault(finTime, "") + req[2]);
                        mentor_copy[req[2]]--;
                        if (waitTime > answer[0] && answer[0] != 0) return; // 조기에 조건에 안맞으면 함수 자체를 탈출
                        break; // 상담 예약 됐으니 더 탐색 불필요.
                    }
                }
            } // 완료
        }
        if(answer[0]==0 || answer[0] > waitTime) answer[0] = waitTime;
    }
}