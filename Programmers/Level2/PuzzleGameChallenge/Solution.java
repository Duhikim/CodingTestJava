package CodingTestStudy.Level2.PuzzleGameChallenge;

public class Solution {
	public int solution(int[] diffs, int[] times, long limit) {
		if(diffs.length==1) return diffs[0];
		int max = 1;
		for(int diff: diffs){
			if(diff>max) max = diff;
		}

		int largest_lv = max, smallest_lv = 1;
		int mid_lv;
		while (largest_lv - smallest_lv > 1) {
			mid_lv = (largest_lv + smallest_lv) / 2;
			long time_temp = getTime(diffs, times, mid_lv);
			if (time_temp == limit)
				return mid_lv;
			else if (time_temp > limit)
			{
				smallest_lv = mid_lv;
			}
			else
			{
				largest_lv = mid_lv;
			}
		}
		return (getTime(diffs, times, smallest_lv) > limit)? largest_lv : smallest_lv;
	}

	public long getTime(int[] diffs, int[] times, int lv) {
		long totalTime = times[0];

		for (int i = 1; i < diffs.length; i++) {
			if (lv >= diffs[i])
			{
				totalTime += times[i];
			}
			else
			{
				totalTime += (diffs[i] - lv) * (times[i - 1] + times[i]) + times[i];
			}
		}
		return totalTime;
	}
}