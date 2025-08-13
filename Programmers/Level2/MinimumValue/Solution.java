package CodingTestStudy.Level2.MinimumValue;

import java.util.Arrays;

public class Solution
{
	public int solution(int []A, int []B)
	{
		int answer = 0;

		Arrays.sort(A);
		Arrays.sort(B);
		reverse(B);

		for(int i=0; i<A.length; i++){
			answer += A[i] * B[i];
		}

		return answer;
	}

	public void reverse(int[] arr){
		int n = arr.length;
		int temp;
		for(int i=0; i<n/2; i++){
			temp = arr[i];
			arr[i] = arr[n-1-i];
			arr[n-1-i] = temp;
		}
	}
}