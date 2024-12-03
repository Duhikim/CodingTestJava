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

        points = new int[][]{{1,1}, {10,10}};
        routes = new int[][]{{1,2}, {1,2}};
        expected = 19;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }

        points = new int[][]{{1,1}, {10,10}, {1, 10}};
        routes = new int[][]{{1,2}, {1,3}};
        expected = 1;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }

        points = new int[][]{{1,1}, {2,2}, {3, 3}};
        routes = new int[][]{{1,2, 1}, {3,2, 1}};
        expected = 3;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }

        points = new int[][]{{1,1}, {1,2}, {1, 3}};
        routes = new int[][]{{2, 1}, {2, 3}};
        expected = 1;
        calculated = sol.solution(points, routes);
        if(expected==calculated){
            System.out.println("Correct : " + calculated);
        }
        else{
            System.out.println("Incorrect : " + expected +" , "+calculated);
        }
        //int[][] points = {{1, 1}, {3, 3}, {6, 6}, {10, 10}};
        //int[][] routes = {{1, 2, 3, 4}, {1, 4, 3, 2}};
        //예상 출력: 2
        //실제 출력: 8
        points = new int[][]{{1, 1}, {3, 3}, {6, 6}, {10, 10}};
        routes = new int[][]{{1, 2, 3, 4}, {1, 4, 3, 2}};
        expected = 8;
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
        int numOfRobots = routes.length;
        Robot.satisfy=0;

        for (int[] route : routes) {
            Robot robot = new Robot(); // (0, 0)에 robot 생성
            robot.setTargets(points, route); // routes에 담겨있는 포인터들의 좌표를 points에서 읽어서 목표 좌표를 배열로 입력한다.
            robot.setNextTarget(); // 다음 목표를 설정하는 함수. 여기서는 첫번째 목표점이 입력된다. (사실상 시작점)

            robots.add(robot);
        }

        // 로봇 만족 카운터가 로봇 개수와 같아질 때 까지 반복.
        while(Robot.satisfy < numOfRobots) {
            // 일단 한칸 움직이자. ( (0, 0)이고 fin이 false인 경우 즉 가장 처음에는 목표점으로 바로 이동 )
            for (Robot robot : robots) {
                if(robot.moveOnce()) { // 움직인 후 목표지점에 도착하면 다음 목표를 설정한다.
                    robot.setNextTarget();
                }
            }
            answer += collisionCheck(robots); // 충돌 체크
        }

        return answer;
    }

    public int collisionCheck(ArrayList<Robot> robots){ // 현재 로봇들의 위치 기준 충돌 위험이 몇 번 있는지 계산하는 함수
        int collision = 0;

        // 현재 robot의 위치들을 Map에 추가하여 같은 위치에 두 대 이상 존재하는 경우 충돌 상황으로 봄.
        HashMap<String, Integer> coordMap = new HashMap<>();
        for (Robot robot : robots) {
            String key = robot.getR() + ","+robot.getC();
            //List<Integer> key = Arrays.asList(robot.getR(), robot.getC()); // 틀리는 원인이 List인가 싶어서 좌표를 String 형식으로 바꿨다.
            coordMap.put(key, coordMap.getOrDefault(key, 0) + 1);
        }

        for(Map.Entry<String, Integer> entry : coordMap.entrySet()) {
            // value값이 2 이상이면 2든 3이든 몇이든 1회 충돌.
            // 같은 시점이어도 여러군데에서 충돌이 발생하는 경우 전부 더해줘야 함.
            // 목적지에 도착하면 escape zone인 (0, 0)으로 이동하는데 이 경우는 제외해줘야 한다.
            if(entry.getValue() > 1 &&
                    !(entry.getKey().equals("0,0"))) {
                collision++;
            }
        }
        return collision;
    }


    public static class Robot{
        public static int satisfy; // 조건 만족 카운터

        private int r; // 현재의 r 좌표. 초기 값은 포인트 정보로 주어진다.
        private int c; // 현재의 c 좌표. 초기 값은 포인트 정보로 주어진다.
        private int re; // 목표 r좌표
        private int ce; // 목표 c좌표
        private int targetIndex;
        private ArrayList<Integer> rTargets;
        private ArrayList<Integer> cTargets;
        private boolean fin;

        public Robot() {
            this.r = 0;
            this.c = 0;
            this.re = 0;
            this.ce = 0;
            this.targetIndex = 0;
            this.rTargets = new ArrayList<>();
            this.cTargets = new ArrayList<>();
            this.fin = false;
        }
        public void setNextTarget(){
            if(targetIndex == rTargets.size()) {
                fin = true; // fin이 true로 바뀐 시점에선 아직 escape zone으로 탈출하지 않음. 마지막 타겟 포인트에 위치.
                return;
            }
            this.re = rTargets.get(targetIndex);
            this.ce = cTargets.get(targetIndex);
            targetIndex++;
        }
        public void setTargets(int[][] points, int[] route){
            for (int ptIdx : route) {
                this.rTargets.add(points[ptIdx - 1][0]);
                this.cTargets.add(points[ptIdx - 1][1]);
            }
        }
        public int getR(){
            return r;
        }
        public int getC(){
            return c;
        }

        public boolean moveOnce(){ // 한칸 움직인 후 목표에 도달하면 true, 아니면 false를 반환.
            if(fin){ // 마지막 move. 최종 타겟 포인트에서 한 번 더움직여서 escape zone (0, 0) 으로 탈출함.
                if(r != 0 && c != 0) {
                    this.r = 0;
                    this.c = 0;
                    satisfy++; // 조건 만족 카운터를 하나 올려준다.
                }
                return false; // 도착했으면 처리 안하겠음.
            }
            if(r==0 && c==0) {
                r = re;
                c = ce;
            }
            if (r != re) {
                r += (r < re) ? 1 : -1;
            } else if (c != ce) {
                c += (c < ce) ? 1 : -1;
            }
            return (r==re && c==ce);
        }
    }
}
