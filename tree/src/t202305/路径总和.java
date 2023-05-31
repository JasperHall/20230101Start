package t202305;

/**
 * https://leetcode.cn/problems/path-sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/30 20:19
 */
public class 路径总和 {  //112.
    class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            targetSum -= root.val;
            // 叶子结点
            if (root.left == null && root.right == null) {
                return targetSum == 0;  // 进入这里说明是到达了叶子节点, 判断此时的 targetSum == 0 是否为true
            }
            if (root.left != null) {  // 左
                boolean left = hasPathSum(root.left, targetSum);
                if (left) {      // 已经找到, left为true, 直接进入if内部返回true
                    return true;
                }
            }
            if (root.right != null) {  // 右
                boolean right = hasPathSum(root.right, targetSum);
                if (right) {     // 已经找到
                    return true;
                }
            }
            return false;
        }
    }
}
