package CodingTestStudy.Level1.PullOutBox;

class Solution {
    public int solution(int n, int w, int num) {
        int len = (n-1) / w + 1;

        // num 위치 찾기
        int row = (num-1)/w;
        int col = ((row&1)==0)? (num-1) % w : w - 1 - (num-1) % w;

        // n 위치 찾기
        int lastRow = (n-1)/w;
        int lastCol = ((lastRow&1)==0)? (n-1) % w : w - 1 - (n-1) % w;

        int answer = len-row;
        // lastRow, col에 위치한 숫자가 n보다 크면 answer에서 1을 뺀다.
        int extraNumber = w*lastRow+1;
        extraNumber += ((lastRow&1)==0)? col : w-1-col;
        if(extraNumber > n) answer--;

        return answer;
    }
}