package graph;

public class ConnectedComponentsTest {
    public static void main(String[] args) {
        AdjacencyList al = new AdjacencyList(8);
        al.addEdge(0,2);
        al.addEdge(0,1);

        al.addEdge(1,4);
        al.addEdge(1,3);
        al.addEdge(1,0);

        al.addEdge(2,6);
        al.addEdge(2,5);
        al.addEdge(2,0);

        al.addEdge(3,7);
        al.addEdge(3,1);

        al.addEdge(4,7);
        al.addEdge(4,1);

        al.addEdge(5,7);
        al.addEdge(5,2);

        al.addEdge(6,7);
        al.addEdge(6,2);

        al.addEdge(7,6);
        al.addEdge(7,5);
        al.addEdge(7,4);
        al.addEdge(7,3);

        ConnectedComponents cc = new ConnectedComponents();
        cc.connectedComponents(al);
    }
}
