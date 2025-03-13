// 148652 : 유사 칸토어 비트열
// https://school.programmers.co.kr/learn/courses/30/lessons/148652

package CodingTestStudy.CantorBit;

public class Solution {
	public int solution(int n, long l, long r) {
		long totalNumberOfOne = (long)Math.pow(4, n);
		long totalNumbers = (long)Math.pow(5, n);

		long numberOfOneBeforeL
				= findNumberOfOneBeforeOrAfterIdx(n, l);
		long numberOfOneAfterR
				= findNumberOfOneBeforeOrAfterIdx(n, (totalNumbers+1)-r );

		return (int)(totalNumberOfOne - numberOfOneAfterR - numberOfOneBeforeL);
	}

	public long findNumberOfOneBeforeOrAfterIdx(int n, long idx){

		long totalNumberOfOne = (long)Math.pow(4, n);
		long totalNumbers = (long)Math.pow(5, n);

		long numberOfOneBeforeIdx = 0;	// l이 어디에 있는지 찾아서 l까지 1의 갯수를 센다.


		while(idx>1){

			if(idx > (totalNumbers/5)*2 && idx <= (totalNumbers/5)*3){
				numberOfOneBeforeIdx += totalNumberOfOne/2;
				break;
			}
			else{

				if(idx > (totalNumbers/5)*3){
					numberOfOneBeforeIdx += totalNumberOfOne/2;
					idx -= (totalNumbers/5)*3;
				}

				if(idx > totalNumbers/5){
					numberOfOneBeforeIdx += totalNumberOfOne/4;
					idx -= totalNumbers/5;
				}
				totalNumbers /= 5;
				totalNumberOfOne /= 4;
			}
		}
		return numberOfOneBeforeIdx;
	}
}

/*
0	1
1	11011
2	11011 11011 00000 11011 11011
3	11011 11011 00000 11011 11011  11011 11011 00000 11011 11011  00000 00000 00000 00000 00000  11011 11011 00000 11011 11011  11011 11011 00000 11011 11011



 */