package t202303;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 20:34
 *
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class 二叉树的后序遍历 {

    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            postorder(root, result);
            return result;
        }
        public void postorder(TreeNode root, List<Integer> result){
            if (root == null){
                return;
            }
            postorder(root.left_Node, result);
            postorder(root.right_Node, result);
            result.add(root.value);
        }
    }
}
