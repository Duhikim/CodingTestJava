package CodingTestStudy.TableEdit;


import java.util.LinkedList;
import java.util.ListIterator;

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


        LinkedList<Integer> dataList = new LinkedList<>();
        LinkedList<Integer> deletedData = new LinkedList<>();
        for(int i=0; i<n; i++){
            dataList.add(i);
        }
//        ListIterator<Integer> iter = dataList.listIterator(k);

        for(String command: cmd){
            switch(command.charAt(0)){
                case 'U':
                    String[] str1 = command.split(" ");
                    k -= (Integer.parseInt(str1[1]));
                    break;
                case 'D':
                    String[] str2 = command.split(" ");
                    k += (Integer.parseInt(str2[1]));
                    break;
                case 'C':
                    deletedData.add(0, dataList.get(k));
                    dataList.remove(k);
                    if(k==dataList.size()) k--;
                    break;
                case 'Z':
                    int temp = deletedData.get(0);
                    if(temp < dataList.get(k)) k++;
                    deletedData.remove(0);
                    ///
                    ListIterator<Integer> iterator = dataList.listIterator();
                    while (iterator.hasNext()) {
                        if (iterator.next() > temp) {
                            // 이전 위치로 되돌린 후 삽입
                            iterator.previous();
                            iterator.add(temp);
                            break;
                        }
                    }
                    if (!iterator.hasNext()) {
                        dataList.add(temp);
                    }
                    break;
            }
        }
        answer.append("O".repeat(Math.max(0, n)));
        for(int num: deletedData){
            answer.setCharAt(num, 'X');
        }

        return answer.toString();
    }
}