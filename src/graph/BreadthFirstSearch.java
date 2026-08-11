package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearch {
    // 방문한 정점 기록
    private boolean[] visited;

    // 방문할 정점 기록
    private Queue<Integer> queue;

    /** 인접 리스트 방식의 그래프를 너비 우선 탐색 */
    public void bfs(AdjacencyList graph, int start) {
        visited = new boolean[graph.vertexCount()];
        queue = new ArrayDeque<>();

        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            System.out.print(vertex + " ");

            for (int next : graph.neighbors(vertex)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

}
