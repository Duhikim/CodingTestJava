package CodingTestStudy.MazeEscapeCommand;

public class Solution2 {

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        int[][] testCases = new int[][]{
                {3,4,2,3,3,1,5},
                {2,2,1,1,2,2,2},
                {3,3,1,2,3,3,4},
                {4,4,2,3,3,2,8},
                {4,4,3,2,2,3,8}
        };
        int tc = 4;

        int n = testCases[tc][0];
        int m = testCases[tc][1];
        int x = testCases[tc][2];
        int y = testCases[tc][3];
        int r = testCases[tc][4];
        int c = testCases[tc][5];
        int k = testCases[tc][6];

        System.out.println(sol.solution(n, m, x, y, r, c, k));
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k){
        char[] dirs = {'d', 'l', 'r', 'u'};
        int[][] posChange = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

        int dist = Math.abs(r - x) + Math.abs(c - y);
        if (dist > k || (dist & 1) != (k & 1)) return "impossible";

        // 현재 상황에서 가장 유리한 것을 가져다가 써보자.
        // d-l-r-u 순서로 유리하다.
        // 다만, 어떤 알파벳을 뽑았을 때, 좌표를 벗어난다거나 목적지보다 멀어지므로써 목적지에 스텝내에 도착할 수 없게되면 다음거
        // 목적지까지 최소 스텝수를 계속 체크하면 될거같음. 만약 목적지에서 멀어지는 방향이면 -1, 가까워지는 방향이면 +1을 하는식으로.
        // 그래서 최소 스텝수가 남은 스텝수보다 커지는 방향은 못간다고 하면 될듯.
        StringBuilder sb = new StringBuilder();
        int[] currPos = new int[]{x,y};
        while(k-- > 0){
            // 1. d를 뽑음
            // 1.1 좌표를 벗어나는지 확인 (벗어나면 취소)
            // 1.2.1 d를 뽑음으로써 목적지에 가까워진다면 스텝수를 하나 줄임
            // 1.2.2 d를 뽑음으로써 목적지에서 멀어진다면 스텝수를 하나 늘림. 만약 늘어난 스텝수가 k보다 크면 취소
            // 위 사이클을 돌리면 됨. 취소되면 그다음인 l로
            for(int i=0; i<4; i++){
                int[] newPos = new int[]{currPos[0]+posChange[i][0], currPos[1]+posChange[i][1]};
                if(newPos[0] > n || newPos[1] < 1) continue; // 범위 벗어나면 탈락
                if(Math.abs(newPos[0] - r) > Math.abs(currPos[0] - r)
                        || Math.abs(newPos[1] - c) > Math.abs(currPos[1] - c)) dist++;
                else dist--;
                if(dist > k) {
                    dist--;
                    continue;
                }
                sb.append(dirs[i]);
                currPos[0] = newPos[0];
                currPos[1] = newPos[1];
                break;
            }
        }
        return sb.toString();
    }
}
