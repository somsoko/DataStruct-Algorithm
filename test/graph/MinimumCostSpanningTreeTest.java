package graph;

public class MinimumCostSpanningTreeTest {
    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        AdjacencyList graph = new AdjacencyList(7,false);
        MinimumCostSpanningTree mst = new MinimumCostSpanningTree();

        graph.addEdge(0,1,28);
        graph.addEdge(1,2,16);
        graph.addEdge(2,3,12);
        graph.addEdge(3,4,22);
        graph.addEdge(4,5,25);
        graph.addEdge(5,0,10);
        graph.addEdge(6,1,14);
        graph.addEdge(6,3,17);
        graph.addEdge(6,4,24);


        AdjacencyList mstK = mst.kruskal(graph);
        bfs.bfs(mstK,0);
    }
}
