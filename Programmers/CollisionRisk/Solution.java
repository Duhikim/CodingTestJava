package CodingTestStudy.CollisionRisk;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] points = new int[0][0];
        int[][] routes = new int[0][0];
        int expected, calculated;
        Solution sol = new Solution();

        points = new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        routes = new int[][]{{4, 2}, {1, 3}, {2, 4}};
        expected = 1;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }

        points = new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        routes = new int[][]{{4, 2}, {1, 3}, {4, 2}, {4, 3}};
        expected = 9;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }

        points = new int[][]{{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        routes = new int[][]{{2, 3, 4, 5}, {1, 3, 4, 5}};
        expected = 0;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }



    }

    public int solution(int[][] points, int[][] routes) {
        /**********
        r, c 좌표로 나타낼 수 있는 n개의 포인트 존재. 각 포인트는 1부터 n까지 고유 번호를 가짐.
        로봇마다 정해진 운송 경로가 존재. 운송 경로는 m개의 포인트로 구성. 로봇은 할당된 포인트를 순서대로 방문
        로봇은 x대이고 0초에 동시 출발. 1초마다 r, c 좌표 중 하나가 1씩 이동함.
        다음 포인트로 이동할때는 항상 최단 경로로 이동. 최단 경로가 여러가지 인 경우 r좌표가 변하는 이동을 c보다 먼저함.
        마지막 포인트에 도착한 로봇은 물료센터를 벗어난다. 로봇이 벗어나는 경로는 고려하지 않음.

        이동 중 같은 좌표에 로봇 2대 이상 모이면 충돌 위험 상황.
        어떤 시간에 여러 좌표에서 충돌 위험 상황이 발생하면 그 횟수를 모두 더한다.
         **
         문제 틀린 이유 : 한 루트의 길이를 무조건 2로 생각함. 하지만 루트의 길이는 최대 100까지 될 수 있다.
         target 좌표를 계속 바꿔주면서 이어나가야 한다.
         **********/
        int answer = 0;

        ArrayList<Robot> robots = new ArrayList<Robot>();
        // routes를 차례대로 읽으면서 첫번째 번호가 의미하는 포인트의 r좌표와 c좌표를 읽어서 robot을 생성한다.
        // 생성 후 routes의 두번째 번호가 의미하는 포인트의 r좌표와 c좌표를 robot의 목적지로 설정한다.
        // 그 후 robot을 robots 배열에 넣고 로봇 수를 센다.
        for (int i = 0; i< routes.length; i++) {
            Robot robot = new Robot(points[routes[i][0] - 1][0], points[routes[i][0] - 1][1]);
            robot.setTarget(points[routes[i][1]-1][0], points[routes[i][1]-1][1]);
            robots.add(robot);
            Robot.numOfRobots++;
        }
        // 로봇 만족 카운터가 로봇 개수와 같아질 때 까지 반복.
        while(Robot.satisfy < Robot.numOfRobots) {

            answer += collisionCheck(robots);

            for (Robot robot : robots) {
                robot.moveOnce();
            } // move로 r, c 좌표가 목적지에 도달한 경우, 아직 fin은 true가 되지 않는다. 다음 사이클에서 true가 됨.
        }

        return answer;
    }

    public int collisionCheck(ArrayList<Robot> robots){ // 현재 로봇들의 위치 기준 충돌 위험이 몇 번 있는지 계산하는 함수
        int collision = 0;

        // 현재 robot의 위치들을 Map에 추가하여 같은 위치에 두 대 이상 존재하는 경우 충돌 상황으로 봄.
        HashMap<List<Integer>, Integer> coordMap = new HashMap<>();
        for (Robot robot : robots) {
                List<Integer> key = Arrays.asList(robot.getR(), robot.getC());
                coordMap.put(key, coordMap.getOrDefault(key, 0) + 1);
        }
        for(Map.Entry<List<Integer>, Integer> entry : coordMap.entrySet()) {
            // value값이 2 이상이면 2든 3이든 몇이든 1회 충돌.
            // 같은 시점이어도 여러군데에서 충돌이 발생하는 경우 전부 더해줘야 함.
            // 목적지에 도착하면 escape zone인 (0, 0)으로 이동하는데 이 경우는 제외해줘야 한다.
            if(entry.getValue() > 1) {
                if (entry.getKey().equals(Robot.escape)) continue;
                collision++;
            }
        }
        return collision;
    }


    public static class Robot{
        public static int satisfy = 0; // 조건 만족 카운터
        public static int numOfRobots; // 로봇 개수. 즉 조건 만족 카운터의 타겟 숫자.
        public static List<Integer> escape = Arrays.asList(0, 0); // 탈출 좌표.

        private int r; // 현재의 r 좌표. 초기 값은 포인트 정보로 주어진다.
        private int c; // 현재의 c 좌표. 초기 값은 포인트 정보로 주어진다.
        private int targetIndex = 0;
        private int[] re; // 목표 r좌표
        private int[] ce; // 목표 c좌표
        private boolean fin;

        public Robot(int r, int c) {
            this.r = r;
            this.c = c;
            this.fin = false;
        }
        public void setTarget(int re, int ce){
            this.re[targetIndex] = re;
            this.ce[targetIndex] = ce;
            targetIndex++;
        }
        public int getR(){
            return r;
        }
        public int getC(){
            return c;
        }
        public void moveOnce(){
            if(fin) return; // 도착했으면 처리 안하겠음.

            if(r !=re) { r = (r <re)? r +1: r -1; }
            else if(c !=ce) { c = (c <ce)? c +1: c -1; }
            else {
                r =0; c =0; // 도착하면 좌표를 0, 0으로 보내고
                fin = true; // fin을 true로 바꾸고
                satisfy++; // 조건 만족 카운터를 하나 올려준다.
            }
        }
    }
}
