package CodingTestStudy.Level3.TableEdit;


import java.util.ArrayDeque;

class Solution {
    public static void main(String[] args) {
        int n, k;
        String[] cmd;
        String expected, calculated;
        Solution sol = new Solution();

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

        DataPointer dataPointer = new DataPointer(n, k);

        for(String command: cmd){
            switch(command.charAt(0)){
                case 'U':
                    String[] str1 = command.split(" ");
                    int up = (Integer.parseInt(str1[1]));
                    dataPointer.up(up);
                    break;
                case 'D':
                    String[] str2 = command.split(" ");
                    int down = (Integer.parseInt(str2[1]));
                    dataPointer.down(down);
                    break;
                case 'C':
                    dataPointer.delete();
                    break;
                case 'Z':
                    dataPointer.undo();
                    break;
            }
        }
        StringBuilder answer = new StringBuilder(n);

        answer.append("O".repeat(n));
        for(RowData rowData: dataPointer.deletedData){
            answer.setCharAt(rowData.getName(), 'X');
        }
        return answer.toString();
    }
    class DataPointer{
        private ArrayDeque<RowData> deletedData;
        private RowData cursor;
        public RowData endData;

        public DataPointer(int n, int k){
            deletedData = new ArrayDeque<>();
            RowData currentData = null;
            RowData prevData = new RowData(0);
            RowData beginData = new RowData(-2);
            beginData.post = prevData;
            prevData.prev = beginData;

            cursor = prevData;

            for(int i=1; i<n; i++){
                currentData = new RowData(i);
                prevData.post = currentData;
                currentData.prev = prevData;
                prevData = currentData;
            }
            currentData.post = endData = new RowData(-1);
            endData.prev = currentData;
            cursor = down(k);
        }

        public RowData up(int X){
            while(X-- != 0){
                cursor = cursor.getPrev();
            }
            return cursor;
        }
        public RowData down(int X){
            while(X-- != 0){
                cursor = cursor.getPost();
            }
            return cursor;
        }
        public void delete(){
            deletedData.addLast(cursor);
            cursor = cursor.remove();
        }
        public void undo(){
            RowData restoring = deletedData.removeLast();
            restoring.restore();
        }
    }

    static class RowData{
        private final int name;
        private RowData prev;
        private RowData post;

        public RowData(int num){
            this.name = num;
            this.prev = null;
            this.post = null;
        }

        public int getName() {
            return name;
        }

        public RowData getPrev() {
            return prev;
        }

        public RowData getPost() {
            return post;
        }

        public RowData remove(){
            prev.post = post;
            post.prev = prev;
            if(post.getName() == -1) return this.prev;
            return this.post;
        }

        public void restore(){
            prev.post = this;
            post.prev = this;
        }
    }


}