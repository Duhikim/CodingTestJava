package CodingTestStudy.Level2.JoyStick;

import java.lang.Math;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();

        // 모든 알파벳을 조작하는 스텝수 적용
        for(int i=0; i<len; i++){
            answer += getSteps(name.charAt(i));
        }
        if(len==1) return answer;

        // 최소 이동하는 스텝 수
        // 가장 긴 연속 A의 길이와 시작지점과 끝지점(선택)을 알면 A를 건너갈지, 돌아갈지 판단할 수 있음.
        // XXXAAAAXXX 이런식으로 A가 있는 파트를 기준으로 세파트로 나눠서 각 길이를 a, b, c라고 할 때
        // 2a + c 혹은 a + 2c가 b보다 작으면 b를 패싱하는게 이득임.
        // 근데 만약 세파트가 아니면 어떻게될까
        // a b c d e f (b, d, f가 연속 A인 파트)일 때,
        // 어느걸 패싱하는게 이득인지 함수 내에서 갱신하면 될거같다.
        answer += movingSteps(name);

        return answer;
    }
    public int getSteps(char c){
        int up = c - 'A';
        int down = 'Z' - c + 1;
        // System.out.println("A를 " + c + "로 바꾸는데 필요한 최소 스텝 : " + Math.min(up, down));
        return Math.min(up, down);
    }
    public int movingSteps(String name){
        int len = name.length();
        int result = len-1;
        int start=0, end=0;
        boolean count = false;
        for(int i=1; i<len; i++){
            if(!count && name.charAt(i) == 'A') {
                start = i;
                count = true;
            }
            if(count && (i+1 >= len || name.charAt(i+1) != 'A')){
                end = i+1;
                // System.out.println("start idx : " + start + " , end idx : " + end);
                int steps = Math.min((start-1 + 2*(len-end)), (2*(start-1) + (len-end))); // 3 5
                result = Math.min(result, steps);
                count = false;
            }
        }
        // System.out.println("최종 " + result + " 스텝");
        return result;
    }

}