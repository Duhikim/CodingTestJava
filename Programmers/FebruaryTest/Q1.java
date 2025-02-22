package CodingTestStudy.FebruaryTest;

import java.io.*;
class Q1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int T = Integer.parseInt(input);
		int[] A = new int[T];
		int[] B = new int[T];

		for(int i=0;i<T; i++){
			input = br.readLine();
			String[] str = input.split(" ");
			A[i] = Integer.parseInt(str[0]);
			B[i] = Integer.parseInt(str[1]);
		}
		int answer = 0;
		for(int i=0; i<T; i++){
			if(Math.max(A[i], B[i]) <= Math.min(A[i],B[i]) * 1.63 &&
					Math.max(A[i], B[i]) >= Math.min(A[i],B[i]) * 1.6){
				answer++;
			}
		}
		System.out.println(answer);
	}
}