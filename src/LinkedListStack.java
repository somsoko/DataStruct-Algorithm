public class LinkedListStack {
    // 연결리스트를 스텍으로 사용
    LinkedList stack = new LinkedList();

    public boolean isEmpty() {
        return stack.head() == null;
    }

    // 연결리스트 맨 앞에 새 노드 추가 <- O(1)에 푸쉬, 팝 가능
    public void push(int data) {
        stack.insert(data,null);
    }

    public int pop() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }
        int result = stack.head().data;
        stack.delete(stack.head(),null);
        return result;
    }

    public void show() {
        stack.show();
    }


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
