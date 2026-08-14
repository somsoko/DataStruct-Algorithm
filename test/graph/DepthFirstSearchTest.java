package graph;

public class DepthFirstSearchTest {
    public static void main(String[] args) {
        AdjacencyList al = new AdjacencyList(8,false);
        al.addEdge(0,2);
        al.addEdge(0,1);

        al.addEdge(1,4);
        al.addEdge(1,3);

        al.addEdge(2,6);
        al.addEdge(2,5);

        al.addEdge(7,6);
        al.addEdge(7,5);
        al.addEdge(7,4);
        al.addEdge(7,3);

        DepthFirstSearch dfs = new DepthFirstSearch();
        dfs.dfs(al,0);

    }

}
