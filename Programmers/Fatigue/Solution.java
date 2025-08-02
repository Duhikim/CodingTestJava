package CodingTestStudy.Fatigue;

class Solution {
    int answer;
    int[][] dungeons;
    public int solution(int k, int[][] dungeons) {
        // 던전의 개수는 8개 이하.
        // 순서의 경우의 수는 8! = 21*1920 = 1920 + 38400 = 대충 4만쯤
        // dfs로 완전탐색

        this.dungeons = dungeons;
        answer = 0;

        dfs(0, k, 0);
        return answer;
    }

    public void dfs(int count, int k, int visit){
        if(count > answer) answer = count;

        for(int i=0; i<dungeons.length; i++){
            if(dungeons[i][0] > k) continue;
            if((visit & (1<<i)) != 0) continue;
            dfs(count+1, k-dungeons[i][1], visit+(1<<i));
        }
    }
}