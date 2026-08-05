public class LinkedListQueue {
    // 연결리스트를 큐로 사용
    private LinkedList queue = new LinkedList();

    // head를 front로 사용
    private LinkedList.Node rear = null;

    public boolean isEmpty() {
        return queue.head() == null;
    }

    // 연결리스트 맨 뒤에 새 노드 추가, rear로 따로 추적 <- O(1)에 인큐, 디큐 가능
    public void add(int data) {
        if(isEmpty()) {
            queue.insert(data,null);
            rear = queue.head();
        }
        else {
            queue.insert(data,rear);
            rear = rear.link;
        }
    }

    // front로 사용하는 head에서 디큐
    public int delete() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }
        int result = queue.head().data;
        queue.delete(queue.head(),null);
        if (isEmpty()) {
            rear = null;
        }
        return result;
    }

    /*
     // 원형 연결리스트로 구현한 큐
     // head를 마지막 노드를 가리키는 last로 사용 <- O(1)에 인큐, 디큐 가능
     // head.link = front, head = rear


    public void add(int data) {
        if(isEmpty()) {
            queue.insert(data,null);
            queue.head().link = queue.head();
        }
        else {
            queue.insert(data,queue.head());
            queue.head = queue.head().link;
        }
    }

    public int delete() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }

        int result = queue.head().link.data;
        // 노드가 하나뿐
        if (queue.head().link == queue.head()) {
            queue.clear();
        }
        else {
            queue.delete(queue.head());
        }

        return result;
    }
    */

    public void show() {
        queue.show();
    }


    public static void main(String[] args) {
        LinkedListQueue lq = new LinkedListQueue();
        lq.add(1);
        lq.add(2);
        lq.add(3);
        lq.show();
        lq.delete();
        lq.show();
        lq.delete();
        lq.show();
        lq.add(4);
        lq.show();
        lq.delete();
        lq.delete();
        lq.delete();
    }
}
