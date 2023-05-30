package t202305;

/**
 * https://leetcode.cn/problems/sum-of-left-leaves/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/30 16:30
 */
public class 左叶子之和 {  // 404
    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) return 0;

            int leftValue = sumOfLeftLeaves(root.left); // 左

            if (root.left != null && root.left.left == null && root.left.right == null){
                leftValue = root.left.val;
            }

            int rightValue = sumOfLeftLeaves(root.right);  // 右

            int sum  = leftValue + rightValue;  // 中
            return sum;
        }
    }
}
