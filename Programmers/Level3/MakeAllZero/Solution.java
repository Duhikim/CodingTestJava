package CodingTestStudy.Level3.MakeAllZero;
/*
한쪽은 +1 한쪽은 -1을 하여 0을 만들어야 하므로
일단 모든 가중치의 합이 0이어야 가능함. (즉 0이 아니면 -1 반환)
모든 가중치의 합이 0이면 반드시 가능한지는 모르겠음. 일단 일직선이면 반드시 가능함.

말단는 한쪽으로밖에 컨트롤이 안되기때문에 말단부터 하는게 좋다.
예제문제를 보면, #1 노드는 가중치가 0이기 때문에 #1과 #0의 간선은 건들지 않는다.
그러면 #0이 말단이 되고, #0은 가중치가 -5이므로 #3과 #0 사이의 간선을 다섯번 연산한다.
그럼 #3은 1에서 -4가 된다.
*/
import java.util.*;
import java.lang.Math;
class Solution {
    boolean debug =
            // true;
            false;
    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        int N = a.length;

        long sum = 0;
        for(int i=0; i<N; i++){
            sum += a[i];
        }
        if(sum != 0) return -1;

        long[] A = new long[N];
        for(int i=0; i<N; i++) A[i] = (long)a[i];
        // 그래프[n] = [a, b, c] >> n번 노드에 a, b, c번 노드가 연결되어있다는 의미
        Set<Integer>[] graph = new HashSet[N];
        for(int i=0; i<N; i++) graph[i] = new HashSet<>();

        for(int[] edge:edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Deque<Integer> terminals = new ArrayDeque<>();
        for(int i=0; i<N; i++) if(graph[i].size()==1) terminals.add(i);

        while(!terminals.isEmpty()){

            int terminal = terminals.poll();
            int next = graph[terminal].iterator().next();
            if(debug)System.out.println("이번 말단 : " + terminal);
            if(debug)System.out.println("연결 노드 : " + next);
            graph[next].remove(terminal);
            A[next] += A[terminal];
            answer += Math.abs(A[terminal]);
            if(debug)System.out.println("연산 횟수 : " + A[terminal] + "추가. 현재 " + answer);
            if(debug)System.out.println(next + "번 노드 가중치 : " + A[next]);
            A[terminal] = 0;
            if(graph[next].size()==1) {
                terminals.add(next);
                if(debug)System.out.println(next + "번 노드 말단에 추가");
            }
            if(graph[next].size()==0){
                if(A[next] != 0) return -1; // 내생각엔 도달하지 않을것 같음
                break;
            }
        }

        return answer;
    }
}