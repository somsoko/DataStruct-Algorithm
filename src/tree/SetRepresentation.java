package tree;

public class SetRepresentation {
    // 배열로 구현
    // 루트는 -집합의 전체 노드 수, 자식은 부모 인덱스를 저장
    private int[] parent;

    public SetRepresentation(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
    }

    /*
    // 루트 인덱스를 반환
    public int find(int i) {
        int index;
        for (index = i; parent[index] >= 0; index = parent[index]) ;

        return index;
    }
    */

    /** 루트 인덱스 반환 */
    // 붕괴 규칙을 적용한 find
    public int find(int i) {
        if (parent[i] < 0) {
            return i;
        }
        parent[i] = find(parent[i]);

        return parent[i];
    }

    /*
    // 같은 집합으로 합치기
    // i를 j의 자식으로 변경
    public void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);

        // 루트 인덱스 변경
        parent[pi] = pj;
    }
    */

    /** 같은 집합으로 합치기 */
    // 가중 규칙을 적용한 union
    public void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi == pj) {
            return;
        }

        // 두 집합의 전체 크기
        int temp = parent[pi] + parent[pj];

        // j가 더 많은 노드 보유 -> j가 더 큰 집합
        if (parent[pi] > parent[pj]) {
            // j가 루트
            parent[pi] = pj;
            parent[pj] = temp;
        }
        // i가 더 큰 집합
        else {
            // i가 루트
            parent[pj] = pi;
            parent[pi] = temp;
        }
    }

}

