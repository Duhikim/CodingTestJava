package CodingTestStudy.Carpet;

public class Solution {
    public int[] solution(int brown, int yellow) {
        // 답이 [a, b]일 때, (a+b-2)*2 == brown, a*b == brown+yellow 여야 한다.
        // a == brown/2 + 2 - b
        // (brown/2)*b +2b - b^2 == brown+yellow
        // b^2 - (brown/2 + 2)b + brown+yellow == 0. 2차방정식 근의공식을 씀.
        // b == ( (brown/2+2) +- route( (brown/2+2)^2 - 4(brown+yellow) ) ) / 2
        // == brown/4 + 1 +- route( ((brown+4)^2) / 16 - (brown+yellow) )
        // 2.5+1 +- 0.5 == 4 or 3

        double a = (double)brown/4 + 1 + Math.sqrt( ( ((double)brown+4) * ((double)brown +4)) /16 - (double)(brown+yellow));
        double b = (double)brown/2 + 2 - a;

        return new int[]{(int)a, (int)b};

    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int brown =
//                  10;
//                  8;
                  24;
        int yellow =
//                  2;
//                  1;
                  24;

        int[] result = sol.solution(brown, yellow);
        System.out.println("["+ result[0] + " , " + result[1] + "]");
    }
}