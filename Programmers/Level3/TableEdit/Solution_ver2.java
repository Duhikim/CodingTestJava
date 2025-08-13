package CodingTestStudy.Level3.TableEdit;

import java.util.ArrayDeque;

class Solution_ver2 {
    public static void main(String[] args) {
        int n, k;
        String[] cmd;
        String expected, calculated;
        Solution_ver2 sol = new Solution_ver2();

        n=8; k=2;
        cmd = new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        expected = new String("OOOOXOOO");

        calculated = new String(sol.solution(n, k, cmd));
        if(expected.equals(calculated)) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);

        n=8; k=2;
        cmd = new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        expected = new String("OOXOXOOO");
        calculated = new String(sol.solution(n, k, cmd));
        if(expected.equals(calculated)) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);

    }

    public String solution(int n, int k, String[] cmd) {
        /************
         표의 범위를 벗어나는 이동은 입력으로 주어지지 않습니다.
         원래대로 복구할 행이 없을 때(즉, 삭제된 행이 없을 때) "Z"가 명령어로 주어지는 경우는 없습니다.
         표의 모든 행을 제거하여, 행이 하나도 남지 않는 경우는 입력으로 주어지지 않습니다.
         정답은 표의 0행부터 n - 1행까지에 해당되는 O, X를 순서대로 이어붙인 문자열 형태로 return 해주세요.
         ************/
        StringBuilder answer = new StringBuilder(n);

        boolean[] deleted = new boolean[n];
        ArrayDeque<Integer> deletedData = new ArrayDeque<>();
        int max = n-1;

        for(String command: cmd){
            switch(command.charAt(0)){
                case 'U':
                    String[] str1 = command.split(" ");
                    int up = (Integer.parseInt(str1[1]));
                    while(up > 0 ) { if(!deleted[--k]) up--; }
                    break;
                case 'D':
                    String[] str2 = command.split(" ");
                    int down = (Integer.parseInt(str2[1]));
                    while(down > 0 ) { if(!deleted[++k]) down--; }
                    break;
                case 'C':
                    deletedData.addLast(k);
                    deleted[k] = true;
                    int temp = k;
                    boolean hasNext = k < max; // max는 최대값
                    while(hasNext && deleted[++k]){}
                    while(!hasNext && deleted[--k]){};
                    if(!hasNext) max = k;
                    break;
                case 'Z':
                    int restore = deletedData.pollLast();
                    deleted[restore] = false;
                    if(restore > max) max = restore;
                    break;
            }
        }
        answer.append("O".repeat(n));
        for(int num: deletedData){
            answer.setCharAt(num, 'X');
        }

        return answer.toString();
    }
}