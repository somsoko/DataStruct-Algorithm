package tree;

public class SelectionTree {
    // 선택트리는 완전 이진트리이기 때문에 배열로 구현
    // 리프노드는 객체 생성, 내부노드는 리프노드의 참조
    private SelectionTreeElement[] selectionTree;

    // 각 run에서 값을 읽을 위치 기록
    private int[] readIndex;

    // 패자 기반 선택트리의 최종 승자
    private SelectionTreeElement resultWinner;

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

        // 리프부터 루트까지 승자 트리 구성
        buildWinner(1,runCount);
    }

    // 승자 트리 구성 재귀 함수
    private SelectionTreeElement buildWinner(int node, int runCount) {
        // 리프노드
        if (node >= runCount) {
            return selectionTree[node];
        }

        SelectionTreeElement leftWinner = buildWinner(node*2,runCount);
        SelectionTreeElement rightWinner = buildWinner(node*2+1,runCount);

        // 현재 노드에 승자 저장
        selectionTree[node] = winner(leftWinner,rightWinner);

        // 현재 서브트리의 승자를 부모에게 전달
        return selectionTree[node];
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

    /** 오름차순으로 정렬된 run들을 받아서 오름차순으로 병합 정렬하는 패자 기반 선택 정렬 */
    public int[] loserSort(int[][] runs) {
        int runCount = runs.length;

        if(runCount == 0) return new int[0];
        if(runCount == 1) return runs[0].clone();

        int total_length = 0;
        for(int[] run : runs) {
            if(run != null)
                total_length += run.length;
        }

        buildLoserTree(runs);

        int[] result = new int[total_length];
        int i = 0;
        while(resultWinner != null) {
            result[i++] = resultWinner.data;
            loserUpdate(runs);
        }

        return result;
    }

    // 초기 트리 구성
    private void buildLoserTree(int[][] runs) {
        int runCount = runs.length;
        selectionTree = new SelectionTreeElement[runCount*2];
        readIndex = new int[runCount];

        // 트리의 리프노드에 각 run의 첫번째 data로 노드 구성
        for(int i = 0; i < runCount; i++) {
            if (runs[i] != null && runs[i].length > 0) {
                selectionTree[runCount+i] = new SelectionTreeElement(runs[i][readIndex[i]++],i);
            }
        }

        // 리프노드부터 루트까지 패자 트리 구성
        // 최종 승자만 별도로 보관
        resultWinner = buildLoser(1,runCount);
    }

    // 패자 트리 구성 재귀 함수
    private SelectionTreeElement buildLoser(int node, int runCount) {
        // 리프노드
        if (node >= runCount) {
            return selectionTree[node];
        }

        SelectionTreeElement leftWinner = buildLoser(node*2,runCount);
        SelectionTreeElement rightWinner = buildLoser(node*2+1,runCount);

        // 현재 노드에는 패자 저장
        selectionTree[node] = loser(leftWinner,rightWinner);

        // 승자는 부모에게 전달
        return winner(leftWinner,rightWinner);
    }

    // 루트 제거 후 트리 재구성
    private void loserUpdate(int[][] runs) {
        int run = resultWinner.run;
        int changeLeaf = readIndex.length + run;

        // 해당 run에 읽을 값이 남아 있는 경우
        if (readIndex[run] < runs[run].length) {
            selectionTree[changeLeaf] = new SelectionTreeElement(runs[run][readIndex[run]++],run);
        }
        else {
            // 해당 run을 모두 읽었으면 제외
            selectionTree[changeLeaf] = null;
        }

        SelectionTreeElement current = selectionTree[changeLeaf];

        // 변경된 리프의 부모부터 루트까지 갱신
        int node = changeLeaf / 2;

        while (node >= 1) {
            SelectionTreeElement old = selectionTree[node];
            selectionTree[node] = loser(current,old);
            current = winner(current,old);

            node /= 2;
        }

        resultWinner = current;
    }

    // 패자 선택
    private SelectionTreeElement loser(SelectionTreeElement left, SelectionTreeElement right) {
        // null은 패자
        if (left == null) return null;
        if (right == null) return null;

        return left.data <= right.data ? right : left;
    }

}

