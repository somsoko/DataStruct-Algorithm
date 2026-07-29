public class Stack {

    private int top;
    private int[] stack;

    public Stack(int size) {
        stack = new int[size];
        top = -1;
    }

    public boolean isFull() {
        return top >= stack.length-1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public void push(int num) {
        if(isFull()) {
            System.out.println("!full!");
            return;
        }
        stack[++top] = num;
    }

    public int pop() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }
        return stack[top--];
    }

    public void show() {
        if(isEmpty()) System.out.println("empty");
        else {
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }

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
