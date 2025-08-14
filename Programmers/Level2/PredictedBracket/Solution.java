package CodingTestStudy.Level2.PredictedBracket;

// 12의 승자 -> 1, 34의 승자 ->2, ... 9/10의 승자 -> 5. 즉 (자기번호+1)/2 가 다음번호가 된다.
// A의 번호와 B의 번호가 연달아 오되 큰번호가 짝수일 때 둘이 만나게 된다.
// N은 2의 20승으로 매우 크지만, 번호는 절반씩 줄어드므로 시간은 문제 없음.
// 그리고 계산에서 N은 쓸이유가 없을 듯
import java.lang.Math;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int temp = Math.max(a, b);
        a = Math.min(a, b);
        b = temp;

        while(b-a != 1 || (b&1)==1 ){
            answer++;
            a = (a+1)/2;
            b = (b+1)/2;
        }

        return answer;
    }
}