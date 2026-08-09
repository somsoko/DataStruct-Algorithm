package tree;

public class BinarySearchTree {
    /** 이진 검색 트리에서 정수 key값과 같은 data를 가지는 노드를 찾아서 리턴 */
    // recursive search
    public BinaryTree.Node search(BinaryTree.Node root, int key) {
        if(root == null) return null;
        if(key == root.data) return root;
        if(key < root.data) return search(root.left_child, key);
        return search(root.right_child, key);
    }

    /*
    // iterative search
    public main.list.tree.BinaryTree.Node search(main.list.tree.BinaryTree.Node node, int key) {
        while(node != null) {
            if(key == node.data) return node;
            if(key < node.data) node = node.left_child;
            else node = node.right_child;
        }
        return null;
    }
    */

    /** 이진 검색 트리에 정수 key값을 data로 가지는 노드 추가 */
    public BinaryTree.Node insert(BinaryTree.Node root, int key) {
        BinaryTree.Node newnNode = new BinaryTree.Node(key);

        if(root == null) return newnNode;

        BinaryTree.Node temp = root;
        while(true) {
            // key 중복 오류
            if(key == temp.data) return null;

            if(key < temp.data) {
                if(temp.left_child == null) {
                    temp.left_child = newnNode;
                    break;
                }
                else temp = temp.left_child;
            }
            else {
                if(temp.right_child == null) {
                    temp.right_child = newnNode;
                    break;
                }
                else temp = temp.right_child;
            }
        }

        return root;
    }

    /** 이진 검색 트리에서 key와 같은 data를 가지는 노드를 삭제 */
    public BinaryTree.Node delete(BinaryTree.Node root, int key) {
        if (root == null) {
            return null;
        }

        // 삭제할 노드 탐색
        if (key < root.data) {
            root.left_child = delete(root.left_child,key);
        }
        else if (key > root.data) {
            root.right_child = delete(root.right_child,key);
        }
        else {
            // 1.리프 노드 또는 오른쪽 자식만 있는 경우
            if (root.left_child == null) {
                return root.right_child;
            }

            // 2.왼쪽 자식만 있는 경우
            if (root.right_child == null) {
                return root.left_child;
            }

            // 3.자식이 둘 다 있는 경우
            // 왼쪽 서브트리에서 가장 큰 노드를 찾아서 현재 노드에 복사
            BinaryTree.Node temp = findMax(root.left_child);
            root.data = temp.data;

            // 복사된 노드 삭제
            root.left_child = delete(root.left_child,temp.data);
        }

        return root;
    }

    // 이진 검색 트리에서 data가 가장 큰 노드 반환
    private BinaryTree.Node findMax(BinaryTree.Node node) {
        while (node.right_child != null) {
            node = node.right_child;
        }

        return node;
    }

}
