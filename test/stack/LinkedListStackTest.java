package stack;

public class LinkedListStackTest {
    public static void main(String[] args) {
        LinkedListStack ls = new LinkedListStack();
        ls.push(1);
        ls.push(2);
        ls.push(3);
        ls.show();
        ls.pop();
        ls.show();
        ls.pop();
        ls.pop();
        ls.pop();
    }
}
