package t202305;

/**
 * https://leetcode.cn/problems/search-in-a-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/31 22:25
 */
public class 二叉搜索树中的搜索 {  // 700. 二叉搜索树中的搜索

    class Solution {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null || root.val ==val ) {
                return root;
            }

            TreeNode left = searchBST(root.left, val);

            if (left != null) return left;

            return searchBST(root.right, val);
        }

        // 递归，利用二叉搜索树特点，优化
        public TreeNode searchBST2(TreeNode root, int val) {
            if (root == null || root.val == val) {
                return root;
            }
            if (val < root.val) {
                return searchBST(root.left, val);
            } else {
                return searchBST(root.right, val);
            }
        }

        //
        public TreeNode searchBST3(TreeNode root, int val) {
            if (root == null || root.val ==val ) {
                return root;
            }

            TreeNode left = searchBST(root.left, val);

            if (left != null) return left;

            TreeNode right = searchBST(root.right, val);

            return right;

        }
    }
}
