package graph;

import java.util.ArrayList;

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

    // 방향성 그래프 여부
    private boolean directed;

    public AdjacencyList(int vertexCount, boolean directed) {
        list = new VertexNode[vertexCount];
        this.directed = directed;
    }

    /** (u -> v) 엣지 추가 */
    public void addEdge(int u, int v) {
        list[u] = new VertexNode(v,list[u]);

        if (!directed) {
            list[v] = new VertexNode(u,list[v]);
        }
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
        if (directed) {
            return inDegree(v) + outDegree(v);
        }

        return outDegree(v);
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
        if (!directed) {
            return outDegree(v);
        }

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

    /** 정점의 총 갯수 리턴 */
    public int vertexCount() {
        return list.length;
    }

    /** 정점과 연결된 이웃을 arrayList에 담아서 리턴 */
    public Iterable<Integer> neighbors(int v) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        VertexNode node = list[v];
        while (node != null) {
            neighbors.add(node.vertex);
            node = node.link;
        }

        return neighbors;
    }

    /** 방향 그래프인지 여부 */
    public boolean isDirected() {
        return directed;
    }
}
