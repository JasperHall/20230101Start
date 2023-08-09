
/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 11:01
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left_Node, TreeNode right_Node){
        this.val = val;
        this.left = left_Node;
        this.right = right_Node;
    }
}
