package CodingTestStudy.Level2.GetPrimeNumber;

import java.util.*;

class Solution {
    // 7개의 숫자로 만들 수 있는 숫자 갯수
    // 7P1 + 7P2 + 7P3 + 7P4 + 7P5 + 7P6 + 7P7
    // = 7 + 7*6 + 7*6*5 + .... + 7*6*5*4*3*2*1 < 7P7 * 7 = 40000 쯤 됨
    // 가장 큰 숫자의 제곱근은 10^3.5 = 10^0.5 * 1000 대략 3500
    // 40000 * 3500 = 140,000,000
    // 시간 초과 안함

    Set<Integer> numberSet;
    String numbers;
    boolean[] visit;

    public int solution(String numbers) {
        int answer = 0;
        numberSet = new HashSet<>();
        this.numbers = numbers;
        this.visit = new boolean[numbers.length()];

        for(int i=1; i<=numbers.length(); i++){
            dfs(i, 0);
        }
        Iterator<Integer> it = numberSet.iterator();
        while(it.hasNext()) {
            int num = it.next();
            if(isPrime(num)) answer++;
        }


        return answer;
    }

    public boolean isPrime(int num){
        if(num<2) return false;
        for(int i=2; i*i<=num; i++){
            if((num % i) == 0) return false;
        }
        return true;
    }

    public void dfs(int len, int madeNum){
        if(madeNum >= power(10, len-1)){
            if(madeNum != 0) numberSet.add(madeNum);
            return;
        }

        for(int i=0; i<numbers.length(); i++){
            int num = (int)(numbers.charAt(i) - '0');
            if(visit[i]) continue;
            visit[i] = true;
            dfs(len, 10*madeNum + num);
            visit[i] = false;
        }
    }

    public int power(int a, int b){
        // a^b
        int n = 1;
        while(b > 0){
            if((b&1)==1) n*=a;
            b >>= 1;
            a*=a;
        }
        return n;
    }
}

