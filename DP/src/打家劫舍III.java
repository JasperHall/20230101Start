import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/house-robber-iii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 10:58
 */
public class 打家劫舍III {  // 337. 打家劫舍 III

    class Solution {
        /**
         * 暴力递归去偷，超时
         * 时间复杂度：O(n^2)，这个时间复杂度不太标准，也不容易准确化，例如越往下的节点重复计算次数就越多
         * 空间复杂度：O(log n)，算上递推系统栈的空间
         * @param root
         * @return
         */
        public int rob(TreeNode root) {
            if (root == null) return 0;
            int money = root.val;
            if (root.left != null) {
                money += rob(root.left.left) + rob(root.left.right);
            }
            if (root.right != null) {
                money += rob(root.right.left) + rob(root.right.right);
            }
            return Math.max(money, rob(root.left) + rob(root.right));
        }

        /**
         * 递归去偷，记录状态
         * // 执行用时：3 ms , 在所有 Java 提交中击败了 56.24% 的用户
         * 时间复杂度：O(n)
         * 空间复杂度：O(log n)，算上递推系统栈的空间
         * @param root
         * @return
         */
        public int rob2(TreeNode root) {
            Map<TreeNode, Integer> memo = new HashMap<>();
            return robAction2(root, memo);
        }

        int robAction2(TreeNode root, Map<TreeNode, Integer> memo) {

            if (root == null) return 0;
            if (memo.containsKey(root)) return memo.get(root);  // 如果计算过了该点就直接返回

            int money = root.val;
            if (root.left != null) {
                money += robAction2(root.left.left, memo) + robAction2(root.left.right, memo);
            }
            if (root.right != null) {
                money += robAction2(root.right.left, memo) + robAction2(root.right.right, memo);
            }
            int res = Math.max(money, robAction2(root.left, memo) + robAction2(root.right, memo));
            memo.put(root, res);
            return res;
        }

        /**
         * // 3.状态标记递归
         * // 执行用时：0 ms , 在所有 Java 提交中击败了 100% 的用户
         * // 不偷：Max(左孩子不偷，左孩子偷) + Max(又孩子不偷，右孩子偷)
         * // root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) +
         * // Math.max(rob(root.right)[0], rob(root.right)[1])
         * // 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
         * // root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
         *
         * 时间复杂度：O(n)，每个节点只遍历了一次
         * 空间复杂度：O(log n)，算上递推系统栈的空间
         * @param root
         * @return
         */
        public int rob3(TreeNode root) {
            int[] res = robAction3(root);
            return Math.max(res[0], res[1]);
        }

        int[] robAction3(TreeNode root) {
            int res[] = new int[2];
            if (root == null) return res;

            // 下标0: 不偷, 下标1:偷
            int[] left = robAction3(root.left);  // 左
            int[] right = robAction3(root.right);  // 右

            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = root.val + left[0] + right[0];
            return res;
        }
    }
}
