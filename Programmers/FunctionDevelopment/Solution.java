package CodingTestStudy.FunctionDevelopment;

import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        int[] progresses, speeds, expected, calculated;
        Solution sol = new Solution();

        progresses = new int[]{93, 30, 55};
        speeds = new int[]{1, 30, 5};
        expected = new int[]{2, 1};
        calculated = sol.solution(progresses, speeds);
        for(int i=0; i<expected.length; i++){
            if(expected[i] != calculated[i]){
                System.out.println("Wrong!");
                break;
            }
            if(i == expected.length-1) System.out.println("Correct!");
        }


        progresses = new int[]{95, 90, 99, 99, 80, 99};
        speeds = new int[]{1, 1, 1, 1, 1, 1};
        expected = new int[]{1,3,2};
        calculated = sol.solution(progresses, speeds);
        for(int i=0; i<expected.length; i++){
            if(expected[i] != calculated[i]){
                System.out.println("Wrong!");
                break;
            }
            if(i == expected.length-1) System.out.println("Correct!");
        }
    }
    public int[] solution(int[] progresses, int[] speeds) {
        int[] remainDays = new int[progresses.length];
        for(int i=0; i<progresses.length; i++) {
            remainDays[i] = (100-progresses[i]-1)/speeds[i]+1; // speed 값으로 나눈 후 올림 해야 함.
        }
        // n번째 항이 배포 될때 n항의 뒤에 있는 항들이 남은 날짜가 더 적다면 한번에 같이 배포한다. n항 배포 날짜보다 느린 항이 올때까지 싹다.
        int idx = 0;
        int counter = 0;
        ArrayList<Integer> answerList = new ArrayList<>();

        for(int i=0; i<progresses.length; i++) {
            if(remainDays[idx] < remainDays[i]) { // 메인 기능 배포 날짜보다 느린 항이 왔다.
                idx = i;
                answerList.add(counter);
                counter=1;
            }
            else {
                counter++;
            }
        }
        answerList.add(counter);

        return answerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}