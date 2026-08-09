package tree;

public class ThreadedBinaryTree {
    // 단일 정수 데이터를 가지는 스레드 이진트리 노드
    public class ThreadNode {
        int data;
        boolean left_thread;
        ThreadNode left_child;
        boolean right_thread;
        ThreadNode right_child;

        public ThreadNode(int data, ThreadNode left_child, ThreadNode right_child) {
            this.data = data;
            this.left_child = left_child;
            this.right_child = right_child;
        }

        public ThreadNode(int data) {
            this(data,null,null);
        }

        public ThreadNode() {
            this(0,null,null);
        }
    }

    /** 빈 스레드 이진트리의 헤드노드 리턴 */
    public ThreadNode create() {
        ThreadNode head = new ThreadNode();
        head.left_child = head;
        head.left_thread = true;
        head.right_child = head;

        return head;
    }

    /** 어떤 한 노드의 inorder successor 리턴 */
    public ThreadNode inorder_successor(ThreadNode node) {
        ThreadNode temp = node.right_child;

        if(!node.right_thread) {
            while(!temp.left_thread) {
                temp = temp.left_child;
            }
        }

        return temp;
    }

    /** 어떤 한 노드의 inorder predecessor 리턴 */
    public ThreadNode inorder_predecessor(ThreadNode node) {
        ThreadNode temp = node.left_child;

        if(!node.left_thread) {
            while(!temp.right_thread) {
                temp = temp.right_child;
            }
        }

        return temp;
    }

    /** 스레드 이진트리의 중위순회 */
    public void inorder_traversal(ThreadNode head) {
        ThreadNode temp = inorder_successor(head);

        while (temp != head) {
            System.out.print(temp.data + " ");
            temp = inorder_successor(temp);
        }
    }

    /** 스레드 이진트리의 부모 오른쪽에 새로운 자식 추가 */
    public void insert_right(ThreadNode parent, ThreadNode newChild) {
        // 새로운 노드 먼저 연결
        newChild.right_child = parent.right_child;
        newChild.right_thread = parent.right_thread;
        newChild.left_child = parent;
        newChild.left_thread = true;

        // 부모 노드 연결
        parent.right_child = newChild;
        parent.right_thread = false;

        // 삽입 전 부모 노드에게 실제 오른쪽 자식이 있었던 경우
        if(!newChild.right_thread) {
            // 삽입 전 부모의 자식 노드와 새로운 노드 연결
            ThreadNode temp = inorder_successor(newChild);
            temp.left_child = newChild;
        }
    }

    /** 스레드 이진트리의 부모 왼쪽에 새로운 자식 추가 */
    public void insert_left(ThreadNode parent, ThreadNode newChild) {
        // 새로운 노드 먼저 연결
        newChild.left_child = parent.left_child;
        newChild.left_thread = parent.left_thread;
        newChild.right_child = parent;
        newChild.right_thread = true;

        // 부모 노드 연결
        parent.left_child = newChild;
        parent.left_thread = false;

        // 삽입 전 부모 노드에게 실제 왼쪽 자식이 있었던 경우
        if(!newChild.left_thread) {
            // 삽입 전 부모의 자식 노드와 새로운 노드 연결
            ThreadNode temp = inorder_predecessor(newChild);
            temp.right_child = newChild;
        }
    }

}
