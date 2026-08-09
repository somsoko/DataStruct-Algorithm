package stack;

public class StackTest {
    public static void main(String[] args) {
        Stack s = new Stack(4);

        s.push(4);
        s.push(7);
        s.push(5);
        s.push(8);

        s.push(66);
        s.show();

        s.pop();
        s.pop();
        s.pop();
        s.show();

        s.pop();
        s.pop();

        s.show();
    }
}
