package t202305;

/**
 * 二叉树节点类的声明
 * Definition for a binary tree node
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 20:23
 */
public class TreeNode {
    int value;
    TreeNode left_Node;
    TreeNode right_Node;
    TreeNode() {}
    TreeNode(int val) { this.value = val; }
    TreeNode(int val, TreeNode left_Node, TreeNode right_Node) {
        this.value = val;
        this.left_Node = left_Node;
        this.right_Node = right_Node;
    }
}
