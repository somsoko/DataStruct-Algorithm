package graph;

public class ConnectedComponents {
    private boolean[] visited;

    /** 인접 리스트 그래프 연결 요소 출력 */
    public void connectedComponents(AdjacencyList graph) {
        visited = new boolean[graph.vertexCount()];

        for (int vertex = 0; vertex < graph.vertexCount(); vertex++) {
            if (!visited[vertex]) {
                dfs(graph, vertex);
                System.out.println();
            }
        }
    }

    // 깊이 우선 탐색
    private void dfs(AdjacencyList graph, int vertex) {
        visited[vertex] = true;
        System.out.print(vertex+"-");

        for (int next : graph.neighbors(vertex)) {
            if (!visited[next]) {
                dfs(graph, next);
            }
        }
    }

}
