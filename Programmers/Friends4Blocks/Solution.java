package CodingTestStudy.Friends4Blocks;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int m, n;
        String[] board;

        m = 4;
        n = 5;
        board = new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"};
        int expected = 14;
        int calculated = new Solution().solution(m, n, board);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! : " + expected +" , " + calculated);

        m = 6;
        n = 6;
        board = new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMTA", "TTTTMm"};
        expected = 15;
        calculated = new Solution().solution(m, n, board);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! : " + expected +" , " + calculated);

    }
    public int solution(int m, int n, String[] board) {
        int[] ans = {0};
        StringBuilder[] boardSb = toStringBuilder(m, board);
        while(popBlocks(m, n, boardSb, ans)){downLines(m, n, boardSb);}
        return ans[0];
    }


    public StringBuilder[] toStringBuilder(int m, String[] board){
        StringBuilder[] boardSb = new StringBuilder[m];
        for(int i=0; i<m; i++){
            StringBuilder sb = new StringBuilder(board[i]);
            boardSb[i] = sb;
        }
        return boardSb;
    }

    public boolean popBlocks(int m, int n, StringBuilder[] boardSb, int[] ans) {
        ArrayList<ArrayList<Integer>> blocks = new ArrayList<>(); // 지워질 블록들의 최 좌상단 좌표들

        for(int i=0; i<m-1; i++){
            for(int j=0; j<n-1; j++){
                if(boardSb[i].charAt(j) != '0'
                        && boardSb[i].charAt(j) == boardSb[i].charAt(j+1)
                        && boardSb[i].charAt(j) == boardSb[i+1].charAt(j)
                        && boardSb[i].charAt(j) == boardSb[i+1].charAt(j+1)){
                    blocks.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        if(!blocks.isEmpty()){
            for (ArrayList<Integer> coord : blocks) {
                int x = coord.get(0);
                int y = coord.get(1);

                if (boardSb[x].charAt(y) != '0') {
                    ans[0]++;
                    boardSb[x].setCharAt(y, '0');
                }
                if (boardSb[x].charAt(y + 1) != '0') {
                    ans[0]++;
                    boardSb[x].setCharAt(y + 1, '0');
                }
                if (boardSb[x + 1].charAt(y) != '0') {
                    ans[0]++;
                    boardSb[x + 1].setCharAt(y, '0');
                }
                if (boardSb[x + 1].charAt(y + 1) != '0') {
                    ans[0]++;
                    boardSb[x + 1].setCharAt(y + 1, '0');
                }
            }
            return true;
        }
        return false;
    }
    public void downLines(int m, int n, StringBuilder[] boardSb) {
        for(int i=m-1; i>0; i--){
            for(int j=0; j<n; j++){
                if(boardSb[i].charAt(j) == '0'){
                    int idx = 0;
                    do{idx++;}while(i-idx >= 0 && boardSb[i-idx].charAt(j) == '0');
                    if(i-idx == -1) continue;
                    boardSb[i].setCharAt(j, boardSb[i-idx].charAt(j));
                    boardSb[i-idx].setCharAt(j, '0');
                }
            }
        }
    }
}