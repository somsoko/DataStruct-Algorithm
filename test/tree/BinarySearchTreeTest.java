package tree;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        BinarySearchTree bst = new BinarySearchTree();

        BinaryTree.Node root = bst.insert(null,40);
        bst.insert(root,20);
        bst.insert(root,10);
        bst.insert(root,60);
        bst.insert(root,30);
        bst.insert(root,50);
        bst.insert(root,45);
        bst.insert(root,70);
        bst.insert(root,55);
        bst.insert(root,52);
        bt.show(root);

        bst.delete(root,60);
        bt.show(root);
    }
}
