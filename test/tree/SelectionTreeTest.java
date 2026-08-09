package tree;

public class SelectionTreeTest {
    public static void main(String[] args) {
        SelectionTree st = new SelectionTree();

        int[][] runs = {
                {1, 5, 9, 13},
                {2, 6, 10},
                {3, 7, 11, 15},
                {4, 8, 12, 14}
        };

        // int[] sorted = st.winnerSort(runs); // 승자 기반 선택 정렬
        int[] sorted = st.loserSort(runs); // 패자 기반 선택 정렬

        for(int i : sorted) {
            System.out.print(i+" ");
        }
    }
}
