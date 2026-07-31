public class DoublyLinkedList {
    // 헤드 노드
    private DoublyNode head;

    // 단일 정수 데이터를 가지는 노드 객체
    public class DoublyNode {
        int data;
        DoublyNode llink;
        DoublyNode rlink;

        public DoublyNode(int data, DoublyNode llink, DoublyNode rlink) {
            this.data = data;
            this.llink = llink;
            this.rlink = rlink;
        }

        public DoublyNode(int data) {
            this(data,null,null);
        }

        public DoublyNode() {
            this(0,null,null);
        }
    }

    public DoublyLinkedList() {
        head = null;
    }

    /** 헤드 노드 리턴 */
    public DoublyNode head() {
        return head;
    }

    /** 이중 연결리스트 초기화 */
    public void clear() {
        head = null;
    }

    /** 이중 연결리스트 길이 리턴 */
    public int length() {
        int count = 0;

        DoublyNode n = head;
        while(n != null) {
            count++;
            n = n.rlink;
        }

        return count;
    }

    /** 정수 데이터와 이전 노드를 받아서 다음 노드에 추가 */
    // before 오른쪽에 추가
    public void insert(int data, DoublyNode before) {
        DoublyNode temp = new DoublyNode(data);

        if(before == null) {
            temp.rlink = head;
            if(head != null) {
                head.llink = temp;
            }
            head = temp;
        }
        else {
            temp.llink = before;
            temp.rlink = before.rlink;
            if(before.rlink != null) {
                before.rlink.llink = temp;
            }
            before.rlink = temp;
        }
    }

    /** 이중 연결리스트의 타겟 노드를 받아서 타겟 노드 삭제 */
    public void delete(DoublyNode target) {
        if (target.llink == null) {
            head = target.rlink;
        }
        else {
            target.llink.rlink = target.rlink;
        }

        if(target.rlink != null) {
            target.rlink.llink = target.llink;
        }
    }

    // 이중 연결리스트 데이터 출력
    public void show() {
        for(DoublyNode n = head; n!=null; n=n.rlink) {
            System.out.print(n.data);
            if (n.rlink != null) {
                System.out.print("<->");
            }
        }
        System.out.println();
    }


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
