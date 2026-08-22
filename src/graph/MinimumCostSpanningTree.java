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
        return null;
    }

    public AdjacencyList sollin(AdjacencyList graph) {
        return null;
    }
}
