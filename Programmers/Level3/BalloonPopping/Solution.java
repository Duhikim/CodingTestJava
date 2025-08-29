package CodingTestStudy.Level3.BalloonPopping;

/*
어떤 풍선이든 그 풍선 기준 왼쪽, 오른쪽에서 하나씩만 남겨서 최종 3개를 남기는 것이 가능함.
이 과정에서 번호가 작은 풍선을 터뜨리는 행위(이하 찬스)를 쓰지 않음
왜냐면 찬스를 써서 지금 풍선보다 큰 풍선을 가져올 수도 있지만, 어차피 찬스는 한 번이므로
그 찬스를 마지막에 써도 상관없기 때문.
그러면 왼쪽, 오른쪽으로 가장 작은 풍선이 배치될 것이다.
이 때 둘 중 적어도 하나가 내 풍선보다 크면 가능, 둘다 작으면 불가능.

양쪽 끝은 항상 가능하다.

a와 같은 크기의 배열을 두 개 만든다. lMin, rMin
lMin는 현재 위치의 왼쪽에서 가장 큰 숫자를 저장
rMin는 현재 위치의 오른쪽에서 가장 큰 숫자를 저장
그 후에 a를 돌면서 a[i]값과 lMin[i], rMin[i]값을 비교하면 될것같음.
*/

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int len = a.length;
        int[] lMin = new int[len];
        int[] rMin = new int[len];
        int INF = Integer.MAX_VALUE;
        lMin[0] = INF;
        rMin[len-1] = INF;

        for(int i=1; i<len; i++){
            lMin[i] = Math.min(lMin[i-1], a[i-1]);
            rMin[len-1-i] = Math.min(rMin[len-i], a[len-i]);
        }

        for(int i=0; i<len; i++){
            if(a[i] < lMin[i] || a[i] < rMin[i]) answer++;
        }
        return answer;
    }
}