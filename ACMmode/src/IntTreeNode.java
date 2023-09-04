/**
 * 节点为int的二叉树
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/3 21:29
 */
public class IntTreeNode {

    int val;
    IntTreeNode left;
    IntTreeNode right;

    IntTreeNode(int val) {
        this.val = val;
    }

    IntTreeNode(int val, IntTreeNode left_Node, IntTreeNode right_Node){
        this.val = val;
        this.left = left_Node;
        this.right = right_Node;
    }
}
