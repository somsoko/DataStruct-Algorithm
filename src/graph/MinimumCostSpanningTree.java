package graph;

import tree.SetRepresentation;

import java.util.ArrayList;
import java.util.Comparator;

public class MinimumCostSpanningTree {
    private class Edge {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public AdjacencyList kruskal(AdjacencyList graph) {
        if (graph.isDirected()) return null;

        AdjacencyList mst = new AdjacencyList(graph.vertexCount(),false);

        ArrayList<Edge> edges = new ArrayList<>();
        // 모든 edge 수집
        for (int u = 0; u < graph.vertexCount(); u++) {
            for (int v : graph.neighbors(u)) {
                // 무방향 그래프에서 같은 간선이 두 번 저장, 중복 제거
                if (u < v) {
                    edges.add(new Edge(u,v,graph.weight(u,v)));
                }
            }
        }

        // weight 오름차순 정렬
        edges.sort(Comparator.comparingInt(e -> e.weight));

        SetRepresentation set = new SetRepresentation(graph.vertexCount());
        for (Edge edge : edges) {
            // cycle 검사
            if (set.find(edge.u) != set.find(edge.v)) {
                set.union(edge.u, edge.v);
                mst.addEdge(edge.u, edge.v, edge.weight);
            }
        }

        return mst;
    }

    public AdjacencyList prim(AdjacencyList graph) {
        if (graph.isDirected()) return null;

        AdjacencyList mst = new AdjacencyList(graph.vertexCount(),false);

        boolean[] visited = new boolean[graph.vertexCount()];

        ArrayList<Edge> edges = new ArrayList<>();

        // 첫 번째 노드와 연결된 간선 등록
        visited[0] = true;
        for (int v : graph.neighbors(0)) {
            edges.add(new Edge(0, v, graph.weight(0, v)));
        }

        while (!edges.isEmpty()) {
            // 등록된 간선 weight 오름차순 정렬 후 최소값 제거
            edges.sort(Comparator.comparingInt(e -> e.weight));
            Edge edge = edges.removeFirst();

            // 방문했다면 제외, 아니라면 mst에 추가 후 방문체크
            if (visited[edge.v]) continue;
            mst.addEdge(edge.u, edge.v, edge.weight);
            visited[edge.v] = true;

            // 추가된 정점과 연결된 간선 등록
            for (int v : graph.neighbors(edge.v)) {
                if (!visited[v])
                    edges.add(new Edge(edge.v, v, graph.weight(edge.v, v)));
            }
        }

        return mst;
    }

    public AdjacencyList sollin(AdjacencyList graph) {
        if (graph.isDirected()) return null;

        AdjacencyList mst = new AdjacencyList(graph.vertexCount(), false);
        SetRepresentation set = new SetRepresentation(graph.vertexCount());

        while (true) {
            Edge[] min = new Edge[graph.vertexCount()];

            // 각 집합의 최소 간선 탐색
            for (int u = 0; u < graph.vertexCount(); u++) {
                for (int v : graph.neighbors(u)) {
                    int a = set.find(u);
                    int b = set.find(v);

                    if (a == b) continue;

                    Edge edge = new Edge(u, v, graph.weight(u, v));

                    if (min[a] == null || min[a].weight > edge.weight)
                        min[a] = edge;

                    if (min[b] == null || min[b].weight > edge.weight)
                        min[b] = edge;
                }
            }

            boolean merged = false;

            // 최소 간선 추가 및 집합 병합
            for (Edge edge : min) {
                if (edge == null) continue;

                if (set.find(edge.u) != set.find(edge.v)) {
                    set.union(edge.u, edge.v);
                    mst.addEdge(edge.u, edge.v, edge.weight);
                    merged = true;
                }
            }

            if (!merged) break;
        }

        return mst;
    }
}
