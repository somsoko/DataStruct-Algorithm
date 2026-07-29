import java.util.ArrayList;

public class SparseMatrix {
    // 희소행렬의 0이 아닌 값을 저장할 ArrayList
    private ArrayList<Element> term = new ArrayList<>();

    // 정수 행, 정수 열, 정수 값을 가지는 객체로 저장
    private class Element {
        int row, column, value;

        public Element(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }

    public SparseMatrix() {
        this(0);
    }
    public SparseMatrix(int num) {
        for(int i=0; i<num+1; i++) {
            term.add(new Element(0, 0, 0));
        }
    }

    public void show() {
        int resultRow = term.getFirst().row;
        int resultCol = term.getFirst().column;

        int[][] matrix = new int[resultRow][resultCol];

        int index = 1;
        for(int i=0; i<resultRow; i++) {
            for(int j=0; j<resultCol; j++) {
                if(index != term.size()) {
                    Element e = term.get(index);

                    if ((e.row == i) && (e.column == j)) {
                        matrix[i][j] = e.value;
                        index++;
                    }
                }
                else break;
            }
        }

        for(int i=0; i<resultRow; i++) {
            for (int j=0; j<resultCol; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        for(Element e : term) {
            System.out.print("("+e.row+","+e.column+","+e.value+")"+" ");
        }
        System.out.println("\n");
    }

    /** 정수 2차원 배열을 받아서 희소행렬 ArrayList 로 변환 */
    public void create_sparseMatrix(int[][] matrix) {
        term.clear();
        term.add(new Element(0,0,0));

        int row = matrix.length;
        int col = matrix[0].length;

        term.getFirst().row = row;
        term.getFirst().column = col;

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(matrix[i][j] != 0) {
                    term.add(new Element(i,j,matrix[i][j]));
                    term.getFirst().value++;
                }
            }
        }
    }

    /** 이 객체의 희소행렬을 전치한 새로운 희소행렬 객체 리턴 */
    public SparseMatrix fast_transpose() {
        SparseMatrix resultMatrix = new SparseMatrix(term.getFirst().value);

        // 전치행렬의 행이 될 열들의 갯수 파악
        int[] resultRow = new int[term.getFirst().column];
        for(int i=1; i<term.size(); i++) {
            resultRow[term.get(i).column]++;
        }

        // 각각의 열들의 갯수로 전치행렬의 각각의 행 시작위치 파악
        int start = 1;
        for(int j=0; j<resultRow.length; j++) {
            int temp = resultRow[j];
            resultRow[j] = start;
            start += temp;
        }

        // 이 객체의 term 을 각각 전치행렬의 위치에 삽입
        for(int k=1; k<term.size(); k++) {
            // 삽입 위치 다음 인덱스에 저장
            int index = resultRow[term.get(k).column]++;

            Element e = resultMatrix.term.get(index);
            e.row = term.get(k).column;
            e.column = term.get(k).row;
            e.value = term.get(k).value;
        }

        resultMatrix.term.getFirst().row = term.getFirst().column;
        resultMatrix.term.getFirst().column = term.getFirst().row;
        resultMatrix.term.getFirst().value = term.getFirst().value;

        return resultMatrix;
    }

    /** 정수 희소행렬 객체를 받아서 이 객체의 희소행렬과 더한 새로운 정수 희소행렬 객체 리턴 */
    public SparseMatrix add(SparseMatrix matrix) {
        if((term.getFirst().row != matrix.term.getFirst().row) && (term.getFirst().column != matrix.term.getFirst().column)) return this;
        else {
            SparseMatrix resultMatrix = new SparseMatrix();

            int i = 1, j = 1;
            Element e1,e2;

            while((i != term.size()) && (j != matrix.term.size())) {
                e1 = term.get(i);
                e2 = matrix.term.get(j);

                if(e1.row == e2.row) {
                    if(e1.column < e2.column) {
                        resultMatrix.term.add(new Element(e1.row, e1.column, e1.value));
                        i++;
                    }
                    else if(e1.column > e2.column) {
                        resultMatrix.term.add(new Element(e2.row, e2.column, e2.value));
                        j++;
                    }
                    else {
                        resultMatrix.term.add(new Element(e1.row, e1.column, e1.value+e2.value));
                        i++;
                        j++;
                    }
                }
                else if(e1.row < e2.row) {
                    resultMatrix.term.add(new Element(e1.row, e1.column, e1.value));
                    i++;
                }
                else {
                    resultMatrix.term.add(new Element(e2.row, e2.column, e2.value));
                    j++;
                }
            }

            while(i != term.size()) {
                e1 = term.get(i);
                resultMatrix.term.add(new Element(e1.row, e1.column, e1.value));
                i++;
            }
            while(j != matrix.term.size()) {
                e2 = matrix.term.get(j);
                resultMatrix.term.add(new Element(e2.row, e2.column, e2.value));
                j++;
            }

            resultMatrix.term.getFirst().row = term.getFirst().row;
            resultMatrix.term.getFirst().column = term.getFirst().column;
            resultMatrix.term.getFirst().value = resultMatrix.term.size()-1;

            return resultMatrix;
        }
    }

    /** 정수 희소행렬을 받아서 이 객체의 희소행렬과 곱한 새로운 정수 희소행렬 객체 리턴 */
    public SparseMatrix mult(SparseMatrix matrix) {
        if(term.getFirst().row != matrix.term.getFirst().column) return this;
        else {
            int[][] tempMatrix = new int[term.getFirst().row][matrix.term.getFirst().column];

            SparseMatrix Tmatrix = matrix.fast_transpose();

            for(int i=1; i<term.size(); i++) {
                Element e1 = term.get(i);
                int row1 = e1.row;
                int col1 = e1.column;

                for(int j=1; j<Tmatrix.term.size(); j++) {
                    Element e2 = Tmatrix.term.get(j);
                    int row2 = e2.row;
                    int col2 = e2.column;

                    if(col1 == col2) {
                        tempMatrix[row1][row2] += e1.value*e2.value;
                    }
                }
            }

            SparseMatrix resultMatrix = new SparseMatrix();
            resultMatrix.create_sparseMatrix(tempMatrix);

            return resultMatrix;
        }
    }


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
