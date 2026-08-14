package graph;

public class ArticulationPointTest {
    public static void main(String[] args) {
        AdjacencyList al = new AdjacencyList(10,false);

        al.addEdge(0,1);
        al.addEdge(1,2);
        al.addEdge(1,3);
        al.addEdge(2,4);
        al.addEdge(3,4);
        al.addEdge(3,5);
        al.addEdge(5,6);
        al.addEdge(5,7);
        al.addEdge(6,7);
        al.addEdge(7,8);
        al.addEdge(7,9);

        ArticulationPoint ap = new ArticulationPoint();
        ap.articulationPoint(al);

    }
}
