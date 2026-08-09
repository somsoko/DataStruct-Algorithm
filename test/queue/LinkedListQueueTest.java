package queue;

public class LinkedListQueueTest {
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
