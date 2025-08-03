package BaekJoon.Q1010_Bridge;

import java.io.*;
import java.util.*;
import java.lang.Math;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            String input = br.readLine();
            String[] str = input.split(" ");
            int m = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);
            int M = Math.max(m, n);
            int N = Math.min(m, n);
            if(N > M/2) N = M-N;
            long answer = 1;
            for(int i=0; i<N; i++){
                answer *= (M-i);
            } for(int i=0; i<N; i++){
                answer /= (N-i);
            } System.out.println(answer);
        }
    }
}