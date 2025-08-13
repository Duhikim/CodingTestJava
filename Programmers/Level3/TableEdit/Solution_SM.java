package CodingTestStudy.Level3.TableEdit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
class Solution_SM {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        Stack<Point> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(i);
        }
        for(int i=0;i<cmd.length;i++){
            String[] S = cmd[i].split(" ");
            switch(S[0]) {
                case "U": // 행 위 이동
                    k -= Integer.parseInt(S[1]);
                    break;
                case "D": // 행 아래 이동
                    k += Integer.parseInt(S[1]);
                    break;
                case "C": // 삭제
                    stack.add(new Point(k,list.remove(k))); // k번째 삭제 key와 value
                    if (k == list.size()) { // 마지막일땐 행 위 이동
                        k--;
                    }
                    break;
                case "Z": // 되돌리기
                    Point p = stack.pop();
                    if(p.x<=k){
                        k++;
                    }
                    list.add(p.x,p.y);
                    break;
            }
        }
        for(int i=0;i<n;i++){
            if(!list.isEmpty()){
                if(i==list.get(0)){
                    sb.append("O");
                    list.remove(0);
                }else{
                    sb.append("X");
                }
            }else{
                sb.append("X");
            }
        }
        return sb.toString();
    }
}