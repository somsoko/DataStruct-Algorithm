package list;

public class DoublyLinkedListTest {
    public static void main(String[] args) {
        DoublyLinkedList s = new DoublyLinkedList();
        s.insert(30,null);
        s.insert(40,s.head());
        s.show();
        s.insert(20,null);
        s.show();
        s.delete(s.head());
        s.show();
        s.insert(10,null);
        s.show();

    }
}
