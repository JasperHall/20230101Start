package t202306;

/**
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/1 19:09
 */
public class 二叉搜索树中的插入操作 {  // 701. 二叉搜索树中的插入操作

    class Solution {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root==null){
                TreeNode node = new TreeNode(val);
                return node;
            }

            if (val < root.val){
                root.left = insertIntoBST(root.left, val);
            }
            if (val > root.val){
                root.right = insertIntoBST(root.right, val);
            }
            return root;
        }
    }
}
