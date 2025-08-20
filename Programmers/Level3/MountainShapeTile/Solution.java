package CodingTestStudy.Level3.MountainShapeTile;

/*
a(n) = a(n-1) * (3+tops[n]) - a(n-2)

*/

class Solution {
    int mod = 10007;
    public int solution(int n, int[] tops) {
        int[] answer = new int[n+1];
        answer[0] = 1;
        answer[1] = 3+tops[0];

        for(int i=2; i<=n; i++){
            answer[i] = ((answer[i-1] * (3+tops[i-1])) % mod - answer[i-2] + mod) % mod;
        }

        return answer[n];
    }
}
