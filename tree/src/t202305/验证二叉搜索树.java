package t202305;

import com.sun.source.tree.Tree;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/31 23:29
 */
public class 验证二叉搜索树 {  // 98. 验证二叉搜索树

    class Solution {
        /**
         * 中序遍历
         */
        TreeNode max;
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            // 左
            boolean left = isValidBST(root.left);
            if (!left){
                return false;
            }

            // 中
            if(max!=null && root.val<=max.val){
                return false;
            }
            max =root;
            // 右
            boolean right = isValidBST(root.right);
            return right;
        }
    }
}
