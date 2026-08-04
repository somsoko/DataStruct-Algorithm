public class MaxHeap {
    // 힙은 완전 이진트리이기 때문에 배열로 구현
    private int[] heap;

    // 현재 힙의 원소 갯수
    private int n = 0;

    public MaxHeap(int size) {
        // 0번은 사용하지 않기 때문에 size+1
        heap = new int[size+1];
    }

    public boolean heapFull() {
        return n == heap.length-1;
    }

    public boolean heapEmpty() {
        return n == 0;
    }

    /** maxheap에 아이템 삽입 */
    public void insert(int item) {
        if(heapFull()) {
            System.out.println("!full!");
            return;
        }

        int i = ++n;
        while((i != 1) && (item > heap[i/2])) {
            heap[i] = heap[i/2];
            i /= 2;
        }
        heap[i] = item;
    }

    /** maxheap의 루트 삭제하고 리턴 */
    public int delete() {
        if(heapEmpty()) {
            System.out.println("!empty!");
            return -1;
        }

        int item = heap[1];
        int temp = heap[n--];

        int parent = 1;
        int child = 2;
        while(child <= n) {
            // 두 개의 자식 중 큰 쪽과 비교
            if((child < n) && (heap[child] < heap[child +1])) {
                child++;
            }
            if(temp > heap[child]) break;
            heap[parent] = heap[child];
            parent = child;
            child *= 2;
        }
        heap[parent] = temp;

        return item;
    }

    // maxheap 출력
    public void show() {
        for(int i = 1; i <= n; i++)
            System.out.print(heap[i]+" ");

        System.out.println();
    }


    public static void main(String[] args) {
        MaxHeap mh = new MaxHeap(6);

        mh.insert(14);
        mh.insert(15);
        mh.insert(10);
        mh.insert(20);
        mh.insert(2);
        mh.insert(5);

        mh.insert(21);
        mh.show();

        System.out.println(mh.delete());
        mh.show();
        System.out.println(mh.delete());
        mh.show();
    }
}
