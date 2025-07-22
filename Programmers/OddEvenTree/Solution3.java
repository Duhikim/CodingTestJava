package CodingTestStudy.OddEvenTree;
import java.util.*;

public class Solution3 {
    public int[] solution(int[] nodes, int[][] edges) {
        // 1. 간선 정보를 바탕으로 인접 리스트 생성
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Set<Integer> visited = new HashSet<>();
        int oddEvenTreeCount = 0;
        int reverseOddEvenTreeCount = 0;

        for (int node : nodes) {
            if (visited.contains(node)) continue;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(node);
            visited.add(node);

            int oddEvenCount = 0;      // 홀짝 노드
            int reverseCount = 0;      // 역홀짝 노드

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int degree = graph.get(cur).size(); // 연결된 간선 수

                if ((cur & 1) == (degree & 1)) {
                    oddEvenCount++;
                } else {
                    reverseCount++;
                }

                for (int neighbor : graph.get(cur)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

            if (oddEvenCount == 1) oddEvenTreeCount++;
            if (reverseCount == 1) reverseOddEvenTreeCount++;
        }

        return new int[]{oddEvenTreeCount, reverseOddEvenTreeCount};
    }
}

