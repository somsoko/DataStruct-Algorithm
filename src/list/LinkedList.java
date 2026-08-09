package list;

public class LinkedList {
    // 헤드 노드
    private Node head;

    public class Node {
        public int data;
        public Node link;

        public Node(int data, Node link) {
            this.data = data;
            this.link = link;
        }

        public Node(int data) {
            this(data,null);
        }

        public Node() {
            this(0,null);
        }
    }

    public LinkedList() {
        head = null;
    }

    /** 헤드 노드 리턴 */
    public Node head() {
        return head;
    }

    /** 연결리스트 초기화 */
    public void clear() {
        head = null;
    }

    /** 연결리스트 길이 리턴 */
    public int length() {
        int count = 0;

        Node n = head;
        while(n != null) {
            count++;
            n = n.link;
        }

        return count;
    }

    /** 정수 데이터를 받아서 연결리스트 끝에 노드 추가 */
    public void add(int data) {
        Node temp = new Node(data);

        if(head == null) {
            head = temp;
        }
        else {
            Node n = head;
            while(n.link != null) {
                n = n.link;
            }
            n.link = temp;
        }
    }

    /** 정수 데이터와 이전 노드를 받아서 다음 노드에 추가 */
    public void insert(int data, Node before) {
        Node temp = new Node(data);

        if(before == null) {
            temp.link = head;
            head = temp;
        }
        else {
            temp.link = before.link;
            before.link = temp;
        }
    }

    /** 연결리스트의 이전 노드를 받아서 다음 노드 삭제, data 리턴 */
    // 첫 노드 삭제 불가능
    public int delete(Node before) {
        if(before.link == null) return -1;
        int data = before.link.data;
        before.link = before.link.link;

        return data;
    }

    /** 연결리스트의 타겟 노드를 받아서 타겟 노드 삭제, data 리턴 */
    // 끝 노드 삭제 불가능
    public int remove(Node target) {
        if(target.link == null) return -1;
        int data = target.data;
        target.data = target.link.data;
        target.link = target.link.link;

        return data;
    }

    /** 연결리스트의 이전 노드와 타겟 노드를 받아서 타겟 노드 삭제, data 리턴 */
    public int delete(Node target, Node before) {
        int data = target.data;
        if(before == null) {
            head = target.link;
        }
        else {
            before.link = target.link;
        }

        return data;
    }

    /** 연결리스트의 링크 방향 반전 */
    public void invert() {
        Node mid = null;
        Node tail;

        while(head != null) {
            tail = mid;
            mid = head;
            head = head.link;
            mid.link = tail;
        }

        head = mid;
    }

    /** 연결리스트를 받아서 이 객체의 연결리스트 뒤에 연결 */
    public void concatenate(LinkedList list) {
        if(head == null) head = list.head;
        else {
            if(list.head != null) {
                Node n = head;
                while(n.link != null) {
                    n = n.link;
                }
                n.link = list.head;
            }
        }
    }

    // 연결리스트 데이터 출력
    public void show() {
        for(Node n=head; n!=null; n=n.link) {
            System.out.print(n.data);
            if (n.link != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }
}
