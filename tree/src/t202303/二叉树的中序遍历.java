package t202303;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 20:30
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class 二叉树的中序遍历 {
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            inorder(root, result);
            return result;
        }
        public void inorder(TreeNode root, List<Integer> result){
            if (root == null){
                return;
            }
            inorder(root.left_Node, result);
            result.add(root.value); // 注意这一句
            inorder(root.right_Node, result);
        }

    }
}
