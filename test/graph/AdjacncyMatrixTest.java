package graph;

public class AdjacncyMatrixTest {
    public static void main(String[] args) {
        AdjacencyMatrix am1 = new AdjacencyMatrix(4,false);

        am1.addEdge(0,1);
        am1.addEdge(0,2);
        am1.addEdge(1,2);
        am1.addEdge(2,3);

        System.out.println(am1.degree(1));
        System.out.println(am1.outDegree(2));


        AdjacencyMatrix am2 = new AdjacencyMatrix(4,true);

        am2.addEdge(0,1);
        am2.addEdge(1,0);
        am2.addEdge(1,2);

        System.out.println(am2.degree(1));
        System.out.println(am2.inDegree(1));
        System.out.println(am2.outDegree(1));
    }
}
