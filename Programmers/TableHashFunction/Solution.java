package CodingTestStudy.TableHashFunction;

import java.util.*;

public class Solution {
	public int solution(int[][] data, int col, int row_begin, int row_end) {

		Arrays.sort(data, Comparator.comparingInt((int[] row) -> row[col-1])
				.thenComparing((row1, row2) -> Integer.compare(row2[0], row1[0]))
		);

		int[] S_ = new int[row_end-row_begin+1];

		for(int i=0; i<row_end-row_begin+1; i++){
			int I = i+row_begin;
			S_[i] = 0;
			for(int j=0; j<data[0].length; j++){
				S_[i] += data[I-1][j]%I;
			}
		}

		int answer = S_[0];
		for(int i=1; i<S_.length; i++){
			answer ^= S_[i];
		}

		return answer;
	}
}
