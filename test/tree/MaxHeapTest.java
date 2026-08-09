package tree;

public class MaxHeapTest {
    public static void main(String[] args) {
        // priority main.queue
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
