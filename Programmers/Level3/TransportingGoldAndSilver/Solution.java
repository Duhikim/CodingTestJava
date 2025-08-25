package CodingTestStudy.Level3.TransportingGoldAndSilver;

class Solution {
    int a, b;
    int[] g, s, w, t;
    int city;

    public boolean isCapable(long T){
        int total = 0;
        int gold = 0;
        int silver = 0;

        for(int i=0; i<city; i++){
            // 몇 번 운반할 수 있는가
            long time = T/t[i];
            if((time&1)==1) time++;
            time >>= 1;

            gold += Math.min(g[i], w[i]*time);
            silver += Math.min(s[i], w[i]*time);
            total += Math.min(g[i]+s[i], w[i]*time);

            if(gold >= a && silver >= b && total >= a+b) return true;
        }
        return false;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.a = a;
        this.b = b;
        this.g = g;
        this.s = s;
        this.w = w;
        this.t = t;
        this.city = g.length;
        if(a+b == 0) return 0L;

        long l=0, r=1;
        while(isCapable(r) == false){
            l = r;
            r <<= 1;
        }

        while(r-l > 1){
            long mid = (l+r)>>1;
            if(isCapable(mid)){
                r = mid;
            } else l = mid;
        }

        return r;
    }
}