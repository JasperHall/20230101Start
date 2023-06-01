package t202305;

import java.util.IllegalFormatCodePointException;
import java.util.Map;

/**
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/1 9:54
 */
public class 二叉搜索树的最小绝对差 {  // 530. 二叉搜索树的最小绝对差

    class Solution {
        TreeNode pre;  // 记录上一个遍历的节点, 中序遍历的话二叉搜索树的值是单调递增的, 所以只记录上一个节点就可以
        int min = Integer.MAX_VALUE;
        public int getMinimumDifference(TreeNode root) {
            findMin(root);
            return min;
        }

        private void findMin(TreeNode root){
            if (root == null) return;

            // 左
            findMin(root.left);

            // 中
            if (pre!=null){
                min = Math.min(min, root.val-pre.val);
            }
            pre = root;  // 赋值当前节点, 给下一次的递归使用
            findMin(root.right);

        }
    }
}
