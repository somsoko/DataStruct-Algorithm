package graph;

public class ArticulationPoint {
    private AdjacencyList graph;

    // 정점 방문 번호
    private int[] visitVertexOrder;

    // 갈 수 있는 가장 위쪽의 정점 방문 번호
    private int[] low;

    // 단절점 여부
    private boolean[] articulationPoint;

    // 방문 순서
    private int visitCount = 0;

    /** 단절점을 찾아서 출력 */
    public void articulationPoint(AdjacencyList graph) {
        this.graph = graph;
        visitVertexOrder = new int[graph.vertexCount()];
        low = new int[graph.vertexCount()];
        articulationPoint = new boolean[graph.vertexCount()];

        dfs(0,-1);

        for (int v = 0; v < articulationPoint.length; v++) {
            if (articulationPoint[v]) {
                System.out.print(v + " ");
            }
        }
    }

    // 깊이 우선 탐색 트리
    private void dfs(int v, int parent) {
        visitVertexOrder[v] = ++visitCount;
        low[v] = visitVertexOrder[v];
        int childCount = 0;

        for (int next : graph.neighbors(v)) {

            // 부모로 돌아가는 엣지는 무시
            if (next == parent) {
                continue;
            }

            // 아직 방문하지 않은 정점
            if (visitVertexOrder[next] == 0) {
                childCount++;

                dfs(next, v);

                low[v] = Math.min(low[v],low[next]);

                // 루트가 아니면서 자식이 조상으로 우회할 수 없음 = 단절점
                if (parent != -1 && low[next] >= visitVertexOrder[v]) {
                    articulationPoint[v] = true;
                }
            }

            // 이미 방문한 정점 = back edge
            else {
                low[v] = Math.min(low[v],visitVertexOrder[next]);
            }
        }

        // 루트이고 자식을 두 개 가짐 = 단절점
        if (parent == -1 && childCount >= 2) {
            articulationPoint[v] = true;
        }
    }
}
