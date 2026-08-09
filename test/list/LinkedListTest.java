package list;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList s = new LinkedList();
        s.add(10);
        s.add(50);
        s.insert(30,s.head());
        s.show();
        s.delete(s.head(),null);
        s.show();
        s.insert(5,null);
        s.show();
        s.invert();
        s.show();
    }
}
