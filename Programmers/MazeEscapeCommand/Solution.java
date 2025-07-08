package CodingTestStudy.MazeEscapeCommand;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
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


        sol.solution(n, m, x, y, r, c, k);
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        Map<String, Integer> requireSteps = new HashMap();
        requireSteps.put("d", 0); // 수직 양수
        requireSteps.put("l", 0); // 수평 음수
        requireSteps.put("r", 0); // 수평 양수
        requireSteps.put("u", 0); // 수직 음수
        // d - l - r - u 순서로 사전순임

        int verTarget = r - x;
        int horTarget = c - y;
        int essenceMoving = abs(verTarget) + abs(horTarget);

        if(verTarget>=0) {
            requireSteps.put("d", abs(verTarget));
        } else requireSteps.put("u", abs(verTarget));

        if(horTarget>=0) {
            requireSteps.put("r", abs(horTarget));
        } else requireSteps.put("l", abs(horTarget));

        // 도착 불가능한 경우
        if(essenceMoving > k || essenceMoving % 2 != k % 2){
            System.out.println("impossible");
            return "impossible";
        }

        String[] dirs = {"d", "l", "r", "u"};

        // 필요한 스텝수가 k와 정확히 맞는 경우
        if(essenceMoving == k){
            StringBuilder sb = new StringBuilder();
            for(String dir : dirs) {
                while (requireSteps.get(dir) > 0) {
                    sb.append(dir);
                    requireSteps.put(dir, requireSteps.get(dir) - 1);
                }
            }
            System.out.println(sb);
            return sb.toString();
        }

        // 사족처리
        // 시작점과 도착점 기준, 최 하단 및 최 좌측에서 추가 여유가 있는지 확인해보자.
        // 1. 최 하단보다 밑으로 여유가 있다면 du 추가, 최하단점 갱신, restSteps -= 2
        // 2. 최 좌측보다 왼쪽으로 여유가 있다면 lr 추가, 최 좌측점 갱신, restSteps -= 2
        // 3. 1, 2 둘다 없는 경우, rl 추가, restSteps -= 2
        // 이것을 restSteps가 0이 될때까지 반복.
        // 나중에 순서만들때는, d-r-l-u 순서로 전부 꺼내 쓰되, 최좌하단점에 도착하면 rl을 반복

        int restSteps = k - essenceMoving;
        int bottomMost = Math.max(x, r);
        int leftMost = Math.min(y, c);

        while(restSteps > 0) {
            if (bottomMost < n) {
                requireSteps.put("d", requireSteps.get("d")+1);
                requireSteps.put("u", requireSteps.get("u")+1);
                bottomMost++;
                restSteps -= 2;
                continue;
            }
            if(leftMost > 1){
                requireSteps.put("l", requireSteps.get("l")+1);
                requireSteps.put("r", requireSteps.get("r")+1);
                leftMost--;
                restSteps -= 2;
                continue;
            }
            requireSteps.put("r", requireSteps.get("r")+restSteps/2);
            requireSteps.put("l", requireSteps.get("l")+restSteps/2);
            break;
        }

        StringBuilder sb = new StringBuilder();
        int[] currPos = {x, y};
        Map<String, int[]> addStep = new HashMap<>();
        addStep.put("d", new int[]{1 , 0});
        addStep.put("l", new int[]{0 , -1});
        addStep.put("r", new int[]{0 , 1});
        addStep.put("u", new int[]{-1 , 0});

        for(String dir: dirs){
            while(requireSteps.get(dir) > 0){
                if(currPos[0]==bottomMost && currPos[1]==leftMost
                        && requireSteps.get("l") > 0){
                    while(requireSteps.get("l") > 0){
                        sb.append("rl");
                        requireSteps.put("r", requireSteps.get("r")-1);
                        requireSteps.put("l", requireSteps.get("l")-1);
                    }
                    continue;
                }
                sb.append(dir);
                currPos[0] += addStep.get(dir)[0];
                currPos[1] += addStep.get(dir)[1];
                requireSteps.put(dir, requireSteps.get(dir)-1);
            }
        }

        System.out.println(sb);
        return sb.toString();
    }
}