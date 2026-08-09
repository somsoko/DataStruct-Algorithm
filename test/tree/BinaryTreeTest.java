package tree;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        BinaryTree.Node two = bt.makeBT(bt.makeBT(null,4,null),2,bt.makeBT(null,5,null));
        BinaryTree.Node three = bt.makeBT(null,3,null);
        BinaryTree.Node one = bt.makeBT(two,1,three);

        bt.show(one);
        System.out.println(bt.node_count(one));
        System.out.println(bt.leaf_node_count(one));
        System.out.println(bt.depth(one));
    }
}
