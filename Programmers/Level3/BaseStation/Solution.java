package CodingTestStudy.Level3.BaseStation;

public class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverLen = 2*w+1;

        int start = 1;
        for (int station : stations) {
            int end = station - w;
            if (start < end) {
                answer += (end - start - 1) / coverLen + 1;
            }
            start = station + w + 1;
            if (start > n) break;
        } if(start<=n)
            answer += (n-start) / coverLen + 1;

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n =
//                11;
                16;
        int[] stations =
//                {4, 11};
                {9};
        int w =
//                1;
                2;
        System.out.println(sol.solution(n, stations, w));

    }
}