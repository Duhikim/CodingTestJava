package CodingTestStudy.TableEdit;


import java.util.HashMap;
import java.util.LinkedList;

class Solution_ver1 {
    public static void main(String[] args) {
        int n, k;
        String[] cmd;
        String expected, calculated;
        Solution_ver1 sol = new Solution_ver1();

//        n=8; k=2;
//        cmd = new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
//        expected = new String("OOOOXOOO");
//
//        calculated = new String(sol.solution(n, k, cmd));
//        if(expected.equals(calculated)) System.out.println("Correct! : " + calculated);
//        else System.out.println("Wrong! answer : " + expected + " , your answer : " + calculated);

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
        StringBuilder answer = new StringBuilder();
        DataManager manager = new DataManager(n, k);

        for(String command: cmd){
            manager.inputCommand(command);
        }

        for(int i=0; i<n; i++){
            if(manager.mapDatas.get(i).isDelete()) answer.append("X");
            else answer.append("O");
        }

        return answer.toString();
    }

    class DataManager{

        private final int size;
        private final HashMap<Integer, RowData> mapDatas;
        private LinkedList<Integer> delDatasIndex;
        private int cursor; // 커서는 이름을 가리킨다.
//        private int lastIndex;


        public DataManager(int n, int k) {
            mapDatas = new HashMap<>();
            delDatasIndex = new LinkedList<>();

            for(int i=0; i<n; i++){
                RowData data = new RowData(i, i);
                mapDatas.put(i, data);
            }
            this.cursor = k;
            this.size = n;
//            this.lastIndex = n-1;
        }

        public RowData findByName(int name){
            return mapDatas.get(name);
        }

        public void inputCommand(String command){
            switch(command.charAt(0)){
                case 'U':
                    String[] str1 = command.split(" ");
                    Up(Integer.parseInt(str1[1]));
                    return;
                case 'D':
                    String[] str2 = command.split(" ");
                    Down(Integer.parseInt(str2[1]));
                    return;
                case 'C': Del(); return;
                case 'Z': Undo(); return;
            }
        }
        public void Up(int X){
            while(X != 0){
                if( !findByName(--cursor).isDelete() )X--; // 커서를 하나씩 내리면서 해당 열이 삭제되었으면 X소비 안함.
            }
        }
        public void Down(int X){
            while(X != 0){
                if( !findByName(++cursor).isDelete() )X--; // 커서를 하나씩 올리면서 해당 열이 삭제되었으면 X소비 안함.
            }
        }
        public void Del(){
            delDatasIndex.add(0, cursor);
            findByName(cursor).setDelete(true);
            int tempCursor = cursor;
            while(findByName(cursor).isDelete()){ // 커서는 삭제되지 않은 다음칸으로 이동. 커서가 마지막 행에 가있으면 전행으로 이동
                cursor++;
                if(cursor == size){
                    cursor = tempCursor;
                    while(findByName(--cursor).isDelete()){}
                    return;
                }
            }
        }
        public void Undo(){
            findByName(delDatasIndex.get(0)).setDelete(false);
            delDatasIndex.remove(0);
        }
    }


    static class RowData{

        private final int name; // 이름은 고유번호로
        private boolean delete;


        public RowData(int name, int rowNum) {
            this.name = name;
            this.delete = false;
        }

        public int getName() {
            return name;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

    }
}