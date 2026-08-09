package tree;

import java.util.LinkedList;

public class BinaryTree {
    // 단일 정수 데이터를 가지는 이진트리 노드
    public static class Node {
        int data;
        Node left_child;
        Node right_child;

        public Node(int data, Node lef_child, Node right_child) {
            this.data = data;
            this.left_child = lef_child;
            this.right_child = right_child;
        }

        public Node(int data) {
            this(data,null,null);
        }

        public Node() {
            this(0,null,null);
        }
    }

    /** 이진트리가 비었는지 리턴 */
    public boolean isEmpty(Node root) {
        return node_count(root) == 0;
    }

    /** leftBT를 왼쪽 서브트리로, rightBT를 오른쪽 서브트리로 가지고, data를 root노드로 가지는 이진트리 리턴*/
    public Node makeBT(Node leftBT, int data, Node rightBT) {
        return new Node(data, leftBT, rightBT);
    }

    /** 오른쪽 서브트리 리턴 */
    public Node right_child(Node root) {
        return root.right_child;
    }

    /** 이진트리 노드 갯수 리턴 */
    public int node_count(Node root) {
        int count = 0;

        if(root != null) {
            count = 1 + node_count(root.left_child) + node_count(root.right_child);
        }
        return count;
    }

    /** 이진트리 리프노드 갯수 리턴 */
    public int leaf_node_count(Node root) {
        int count = 0;

        if(root != null) {
            if(root.left_child == null && root.right_child == null) return 1;
            else count = leaf_node_count(root.left_child) + leaf_node_count(root.right_child);
        }
        return count;
    }

    /** 이진트리 깊이 리턴 */
    public int depth(Node root) {
        int depth = 0;

        if(root != null) {
            int left = depth(root.left_child);
            int right = depth(root.right_child);
            depth = 1 + (left > right ? left : right);
        }
        return depth;
    }

    /** 이진트리 복사 */
    // postorder traversal
    public Node copy(Node root) {
        if(root != null) {
            Node temp = new Node();
            temp.left_child = copy(root.left_child);
            temp.right_child = copy(root.right_child);
            temp.data = root.data;

            return temp;
        }
        return null;
    }

    /** 이진트리 동일성 검사 */
    // preorder traversal
    public boolean equal(Node first, Node second) {
        return ((first == null && second == null) ||
                (first != null && second != null &&
                        (first.data == second.data) &&
                        equal(first.left_child,second.left_child) &&
                        equal(first.right_child,second.right_child)
                )
        );
    }

    // inorder traversal
    public void inorder_show(Node node) {
        if(node != null) {
            inorder_show(node.left_child);
            System.out.print(node.data+" ");
            inorder_show(node.right_child);
        }
    }

    // level order traversal (BFS)
    public void show(Node root) {
        LinkedList<Node> queue = new LinkedList<>();

        if(root != null) {
            queue.addLast(root);

            while(!queue.isEmpty()) {
                Node temp = queue.removeFirst();
                System.out.print(temp.data+" ");

                if(temp.left_child != null) {
                    queue.addLast(temp.left_child);
                }
                if(temp.right_child != null) {
                    queue.addLast(temp.right_child);
                }
            }
        }
        System.out.println();
    }

}
