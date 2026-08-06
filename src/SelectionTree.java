public class SelectionTree {
    // 선택트리는 완전 이진트리이기 때문에 배열로 구현
    private SelectionTreeElement[] selectionTree;

    // 각 run에서 값을 읽을 위치 기록
    private int[] readIndex;

    // 정수 key와 단일 정수 data를 가지는 Element
    public class SelectionTreeElement {
        int data;
        int run;

        public SelectionTreeElement(int data, int run) {
            this.data = data;
            this.run = run;
        }
    }

    /** 오름차순으로 정렬된 run들을 받아서 오름차순으로 병합 정렬하는 승자 기반 선택 정렬 */
    public int[] winnerSort(int[][] runs) {
        int runCount = runs.length;

        if(runCount == 0) return new int[0];
        if(runCount == 1) return runs[0].clone();

        int total_length = 0;
        for(int[] run : runs) {
            if(run != null)
                total_length += run.length;
        }

        buildWinnerTree(runs);

        int[] result = new int[total_length];
        int i = 0;
        while(selectionTree[1] != null) {
            result[i++] = selectionTree[1].data;
            winnerUpdate(runs);
        }

        return result;
    }

    // 초기 트리 구성
    private void buildWinnerTree(int[][] runs) {
        int runCount = runs.length;
        selectionTree = new SelectionTreeElement[runCount*2];
        readIndex = new int[runCount];

        // 트리의 리프노드에 각 run의 첫번째 data로 노드 구성
        for(int i = 0; i < runCount; i++) {
            if (runs[i] != null && runs[i].length > 0) {
                selectionTree[runCount+i] = new SelectionTreeElement(runs[i][readIndex[i]++],i);
            }
        }

        // 리프노드의 부모 구성
        for (int node = runCount - 1; node >= 1; node--) {
            selectionTree[node] = winner(selectionTree[node*2],selectionTree[node*2+1]);
        }

    }

    // 루트 제거 후 트리 재구성
    private void winnerUpdate(int[][] runs) {
        SelectionTreeElement root = selectionTree[1];
        int run = root.run;
        int changeLeaf = readIndex.length + run;

        // 해당 run에 읽을 값이 남아 있는 경우
        if (readIndex[run] < runs[run].length) {
            selectionTree[changeLeaf] = new SelectionTreeElement(runs[run][readIndex[run]++],run);
        }
        else {
            // 해당 run을 모두 읽었으면 제외
            selectionTree[changeLeaf] = null;
        }

        // 변경된 리프의 부모부터 루트까지 갱신
        int node = changeLeaf / 2;

        while (node >= 1) {
            selectionTree[node] = winner(selectionTree[node*2],selectionTree[node*2+1]);

            node /= 2;
        }
    }

    // 승자 선택
    private SelectionTreeElement winner(SelectionTreeElement left, SelectionTreeElement right) {
        if (left == null) return right;
        if (right == null) return left;

        return left.data <= right.data ? left : right;
    }


    public static void main(String[] args) {
        SelectionTree st = new SelectionTree();

        int[][] runs = {
                {1, 5, 9, 13},
                {2, 6, 10},
                {3, 7, 11, 15},
                {4, 8, 12, 14}
        };

        int[] sorted = st.winnerSort(runs);
        for(int i : sorted) {
            System.out.print(i+" ");
        }
    }
}

