package CodingTestStudy.Level2.MaxAndMin;

import java.util.Arrays;

public class Solution {
	public String solution(String s) {
		String[] str = s.split(" ");
		int[] intArr = Arrays.stream(str)
				.mapToInt(Integer::parseInt)
				.toArray();
		Arrays.sort(intArr);

		return intArr[0] + " " + intArr[intArr.length-1];
	}
}