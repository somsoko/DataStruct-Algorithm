package queue;

import list.LinkedList;

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
        int result = queue.delete(queue.head(),null);

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
            main.queue.insert(data,null);
            main.queue.head().link = main.queue.head();
        }
        else {
            main.queue.insert(data,main.queue.head());
            main.queue.head = main.queue.head().link;
        }
    }

    public int delete() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }

        // 노드가 하나뿐
        if (main.queue.head().link == main.queue.head()) {
            int result = main.queue.head().data;
            main.queue.clear();
        }

        return main.queue.delete(main.queue.head());
    }
    */

    public void show() {
        queue.show();
    }

}
