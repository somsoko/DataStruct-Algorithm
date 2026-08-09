package application;

public class SparseMatrixTest {
    public static void main(String[] args) {
        int[][] a = {{0,2,1},{1,0,1}};
        int[][] b = {{0,1},{3,0},{0,1}};

        SparseMatrix s = new SparseMatrix();
        SparseMatrix s2 = new SparseMatrix();

        s.create_sparseMatrix(a);
        s2.create_sparseMatrix(b);

        s.show();
        s2.show();

        SparseMatrix s3 = s2.fast_transpose();
        s3.show();

        SparseMatrix s4 = s.mult(s2);
        s4.show();

        s = s.add(s3);
        s.show();

    }
}
