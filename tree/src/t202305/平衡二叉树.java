package t202305;

/**
 * https://leetcode.cn/problems/balanced-binary-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/30 0:48
 */
public class 平衡二叉树 {  //110

    class Solution {
        /**
         * 递归法
         * @param root
         * @return
         */
        public boolean isBalanced(TreeNode root) {
            return getHeight(root) != -1;  // 不等于-1,则证明是平衡二叉树
        }
        private int getHeight(TreeNode root){
            if (root == null) return 0;

            int leftHeight = getHeight(root.left);
            if (leftHeight == -1) return -1;

            int rightHeight = getHeight(root.right);
            if (rightHeight == -1) return -1;

            // 左右子树高度差大于1，return -1表示已经不是平衡树了
            if (Math.abs(leftHeight - rightHeight) > 1) return -1;

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
