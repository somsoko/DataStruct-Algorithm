public class MaxHeap {
    // 힙은 완전 이진트리이기 때문에 배열로 구현
    private Element[] heap;

    // 현재 힙의 원소 갯수
    private int n = 0;

    // 정수 key와 단일 정수 data를 가지는 Element
    public class Element {
        int key;
        int data;

        public Element(int key, int data) {
            this.key = key;
            this.data = data;
        }
    }

    public MaxHeap(int size) {
        // 0번은 사용하지 않기 때문에 size+1
        heap = new Element[size+1];
    }

    public boolean heapFull() {
        return n == heap.length-1;
    }

    public boolean heapEmpty() {
        return n == 0;
    }

    /** maxheap에 아이템 삽입 */
    public void insert(Element item) {
        if(heapFull()) {
            System.out.println("!full!");
            return;
        }

        int i = ++n;
        while((i != 1) && (item.key > heap[i/2].key)) {
            heap[i] = heap[i/2];
            i /= 2;
        }
        heap[i] = item;
    }

    /** maxheap의 루트 삭제하고 리턴 */
    public Element delete() {
        if(heapEmpty()) {
            System.out.println("!empty!");
            return null;
        }

        Element item = heap[1];
        Element temp = heap[n];
        heap[n] = null;
        n--;

        int parent = 1;
        int child = 2;
        while(child <= n) {
            // 두 개의 자식 중 큰 쪽과 비교
            if((child < n) && (heap[child].key < heap[child +1].key)) {
                child++;
            }
            if(temp.key > heap[child].key) break;
            heap[parent] = heap[child];
            parent = child;
            child *= 2;
        }
        heap[parent] = temp;

        return item;
    }

    // maxheap의 element의 data 출력
    public void show() {
        for(int i = 1; i <= n; i++)
            System.out.print(heap[i].data+" ");

        System.out.println();
    }


    public static void main(String[] args) {
        // priority queue
        MaxHeap pq = new MaxHeap(6);

        pq.insert(pq.new Element(14,14));
        pq.insert(pq.new Element(15,15));
        pq.insert(pq.new Element(10,10));
        pq.insert(pq.new Element(20,20));
        pq.insert(pq.new Element(2,2));
        pq.insert(pq.new Element(5,5));

        pq.insert(pq.new Element(21,21));
        pq.show();

        System.out.println(pq.delete().data);
        pq.show();
        System.out.println(pq.delete().data);
        pq.show();
    }
}
