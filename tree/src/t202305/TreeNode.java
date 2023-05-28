package t202305;

/**
 * 二叉树节点类的声明
 * Definition for a binary tree node
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 20:23
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left_Node, TreeNode right_Node) {
        this.val = val;
        this.left = left_Node;
        this.right = right_Node;
    }
}
