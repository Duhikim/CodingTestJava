package CodingTestStudy.Level3.AvantGardeTile;

// a(n) = a(n-1) * b(1) + a(n-2)*b(2) + a(n-3)*b(3)
//          + a(n-4)*b(4) + a(n-5)*b(5) + a(n-6)*b(6)
//          + a(n-7)*b(7) + a(n-8)*b(8) + a(n-9)*b(9)
//          + a(n-10)*b(10) + a(n-11)*b(11) + a(n-12)*b(12)
//          ....
//          + a(2) * b(n-2) + a(1) * b(n-1) + a(0) * b(n);
// b( ) = {0, 1, 2, 5, 2, 2, 4, 2, 2, 4, 2, 2, 4, ....}

class Solution {
    int mod = 1_000_000_007;

    public int solution(int n) {
        long[] dp = new long[3];
        dp[0] = 10;
        dp[1] = 1;
        dp[2] = 3;
        long[] sums = new long[3];
        sums[0] += 11;
        sums[1] += 1;
        sums[2] += 3;

        for(int i=4; i<=n; i++){
            int idx = i%3;
            switch(idx){
                case 0: dp[idx] = ((sums[0] * 4)%mod + (sums[1] * 2)%mod + (sums[2] * 2)%mod - dp[2] + dp[0] + mod) % mod; break;
                case 1: dp[idx] = ((sums[0] * 2)%mod + (sums[1] * 4)%mod + (sums[2] * 2)%mod - dp[0] + dp[1] + mod) % mod; break;
                case 2: dp[idx] = ((sums[0] * 2)%mod + (sums[1] * 2)%mod + (sums[2] * 4)%mod - dp[1] + dp[2] + mod) % mod; break;
            }
            sums[idx] += dp[idx];
            sums[idx] %= mod;
        }

        return (int)dp[n%3];
    }
}