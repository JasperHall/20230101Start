package t202305;

/**
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/29 18:27
 */
public class 二叉树的最小深度 {  // 111
    class Solution {
        /**
         * 递归法, 比求最大深度要复杂一点
         * 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
         * 后序遍历
         * @param root
         * @return
         */
        public int minDepth(TreeNode root) {
            if (root == null) return 0;

            int leftDepth = minDepth(root.left);
            int rightDepth = minDepth(root.right);

            // 这里得判断空值情况, 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
            // 有叶子结点在算话
            if (root.left == null){
                return rightDepth+1;
            }else if (root.right == null){
                return leftDepth+1;
            }

            // 左右节点都不为空时
            return Math.min(leftDepth, rightDepth) + 1;
        }

        /**
         * 精简版
         * @param root
         * @return
         */
        public int minDepth2(TreeNode root) {
            if (root == null) return 0;

            if (root.left==null && root.right!=null) return 1+minDepth2(root.right);

            if (root.left!=null && root.right==null) return 1+minDepth2(root.left);

            return 1 + Math.min(minDepth2(root.left), minDepth2(root.right));
        }

    }
}
