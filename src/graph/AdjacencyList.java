package graph;

public class AdjacencyList {
    // 인접 리스트 노드
    private class VertexNode {
        int vertex;
        VertexNode link;

        public VertexNode(int vertex, VertexNode link) {
            this.vertex = vertex;
            this.link = link;
        }
    }

    // 인접 리스트 헤드노드
    private VertexNode[] list;

    public AdjacencyList(int vertexCount) {
        list = new VertexNode[vertexCount];
    }

    /** (u -> v) 엣지 추가 */
    public void addEdge(int u, int v) {
        list[u] = new VertexNode(v,list[u]);
    }

    /** (u -> v) 엣지 제거 */
    public void removeEdge(int u, int v) {
        VertexNode current = list[u];
        VertexNode previous = null;

        while (current != null) {
            if (current.vertex == v) {
                if (previous == null) {
                    list[u] = current.link;
                }
                else {
                    previous.link = current.link;
                }
                return;
            }

            previous = current;
            current = current.link;
        }
    }

    /** (u -> v) 엣지 존재 여부 리턴 */
    public boolean hasEdge(int u, int v) {
        VertexNode node = list[u];
        while (node != null) {
            if (node.vertex == v) {
                return true;
            }
            node = node.link;
        }

        return false;
    }

    /** 정점 v의 전체 차수 */
    public int degree(int v) {
        return inDegree(v) + outDegree(v);
    }

    /** 정점 v에서 나가는 엣지 수 */
    public int outDegree(int v) {
        int count = 0;

        VertexNode node = list[v];
        while (node != null) {
            count++;
            node = node.link;
        }

        return count;
    }

    /** 정점 v로 들어오는 엣지 수 */
    public int inDegree(int v) {
        int count = 0;

        for (int u = 0; u < list.length; u++) {
            VertexNode node = list[u];

            while (node != null) {
                if (node.vertex == v) {
                    count++;
                }

                node = node.link;
            }
        }

        return count;
    }
}
