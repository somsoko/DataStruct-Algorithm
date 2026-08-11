package graph;

public class DepthFirstSearch {
    // 방문한 정점 기록
    private boolean[] visited;

    /** 인접 리스트 방식의 그래프를 깊이 우선 탐색 */
    public void dfs(AdjacencyList graph, int start) {
        visited = new boolean[graph.vertexCount()];
        search(graph,start);
    }

    // 재귀적으로 깊이 우선 탐색
    private void search(AdjacencyList graph, int start) {
        visited[start] = true;
        System.out.print(start+" ");

        for(int vertex : graph.neighbors(start)) {
            if (!visited[vertex]) {
                search(graph,vertex);
            }
        }
    }

}
