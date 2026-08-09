package stack;

import list.LinkedList;

public class LinkedListStack {
    // 연결리스트를 스텍으로 사용
    private LinkedList stack = new LinkedList();

    // head를 top으로 사용

    public boolean isEmpty() {
        return stack.head() == null;
    }

    // 연결리스트 맨 앞에 새 노드 추가 <- O(1)에 푸쉬, 팝 가능
    public void push(int data) {
        stack.insert(data,null);
    }

    // top으로 사용하는 head에서 팝
    public int pop() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }

        return stack.delete(stack.head(),null);
    }

    public void show() {
        stack.show();
    }

}
