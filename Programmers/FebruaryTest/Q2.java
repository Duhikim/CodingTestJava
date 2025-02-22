package CodingTestStudy.FebruaryTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		int[] D = new int[N];
		int[] S = new int[N];
		for(int i=0; i<N; i++){
			String[] str = br.readLine().split(" ");
			D[i] = Integer.parseInt(str[0]);
			S[i] = Integer.parseInt(str[1]);
		}
		int[] Total = new int[N];
		for(int rate=1; rate<=99; rate++){
			int[] temp = new int[N];
			for(int i=0; i<N; i++){
				temp[i] = D[i]*rate + S[i]*(100-rate);
			}
			int max = temp[0];
			int maxIdx = 0;
			for(int i=1; i<N; i++){
				if(temp[i] > max){
					max = temp[i];
					maxIdx = i;
				}
			}
			Total[maxIdx]++;
		}
		for(int i=0; i<N; i++){
			System.out.print(Total[i]);
			if(i < N-1) System.out.print(" ");
		}
	}
}