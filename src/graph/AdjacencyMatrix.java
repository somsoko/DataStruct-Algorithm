package graph;

public class AdjacencyMatrix {
    // 인접 행렬
    private boolean[][] matrix;

    // 방향성 그래프 여부
    private boolean directed;

    public AdjacencyMatrix(int vertexCount, boolean directed) {
        matrix = new boolean[vertexCount][vertexCount];
        this.directed = directed;
    }

    /** (u -> v) 엣지 추가 */
    public void addEdge(int u, int v) {
        matrix[u][v] = true;

        if (!directed) {
            matrix[v][u] = true;
        }
    }

    /** (u -> v) 엣지 제거 */
    public void removeEdge(int u, int v) {
        matrix[u][v] = false;

        if (!directed) {
            matrix[v][u] = false;
        }
    }

    /** (u -> v) 엣지 존재 여부 리턴 */
    public boolean hasEdge(int u, int v) {
        return matrix[u][v];
    }

    /** 정점 v의 전체 차수 */
    public int degree(int v) {
        if (directed) {
            return inDegree(v) + outDegree(v);
        }

        return outDegree(v);
    }

    /** 정점 v에서 나가는 엣지 수 */
    public int outDegree(int v) {
        int count = 0;

        for (int u = 0; u < matrix.length; u++) {
            if (matrix[v][u]) {
                count++;
            }
        }

        return count;
    }

    /** 정점 v로 들어오는 엣지 수 */
    public int inDegree(int v) {
        int count = 0;

        for (int u = 0; u < matrix.length; u++) {
            if (matrix[u][v]) {
                count++;
            }
        }

        return count;
    }
}
