package t202305;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/29 16:40
 */
public class 二叉树的最大深度 {  // 104
    class Solution {
        /**
         * 递归法 求高度法 后序遍历
         * @param root
         * @return
         */
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;

            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            return Math.max(leftDepth, rightDepth) + 1;
        }

        /**
         * 递归法  求深度法  前序遍历
         * @param root
         * @return
         */
        int maxNum = 0;
        public int maxDepth2(TreeNode root) {
            ans(root, 0);
            return maxNum;
        }
        // 递归求最大深度
        private void ans(TreeNode tree, int temp){
            if (tree == null) return;
            temp++;
            maxNum = maxNum<temp ? temp: maxNum;  // maxNum = Math.max(maxNum, temp);
            ans(tree.left, temp);
            ans(tree.right, temp);
            temp--;

        }
    }
}
