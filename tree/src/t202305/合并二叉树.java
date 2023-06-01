package t202305;

/**
 * https://leetcode.cn/problems/merge-two-binary-trees/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/31 20:41
 */
public class 合并二叉树 {  // 617. 合并二叉树

    class Solution {
        /**
         * 递归  前序遍历
         * @param root1
         * @param root2
         * @return
         */
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) return root2;  // 如果roo1为空，合并之后就应该是root2
            if (root2 == null) return root1;  // 如果root2为空，合并之后就应该是root1

            // 修改root1的数值和结构
            root1.val += root2.val;  // 中

            root1.left = mergeTrees(root1.left, root2.left);  // 左
            root1.right = mergeTrees(root1.right, root2.right);  // 右

            return root1;
        }

        /**
         * 重新定义一个树的方法
         * @param root1
         * @param root2
         * @return
         */
        public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
            if (root1 == null) return root2;  // 如果roo1为空，合并之后就应该是root2
            if (root2 == null) return root1;  // 如果root2为空，合并之后就应该是root1

            // 重新定义新的节点，不修改原有两个树的结构
            TreeNode tree = new TreeNode(0);

            tree.val = root1.val + root2.val;  // 中
            tree.left = mergeTrees(root1.left, root2.left);  // 左
            tree.right = mergeTrees(root1.right, root2.right);  // 右

            return tree;
        }
    }
}
