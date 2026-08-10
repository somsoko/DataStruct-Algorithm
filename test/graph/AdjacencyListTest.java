package graph;

public class AdjacencyListTest {
    public static void main(String[] args) {
        AdjacencyList al = new AdjacencyList(4);

        al.addEdge(0,1);
        al.addEdge(1,0);
        al.addEdge(1,2);

        System.out.println(al.degree(1));
        System.out.println(al.inDegree(1));
        System.out.println(al.outDegree(1));
    }
}
