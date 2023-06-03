package t202306;

/**
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/2 20:54
 */
public class 修剪二叉搜索树 {  // 669. 修剪二叉搜索树

    class Solution {
        public TreeNode trimBST(TreeNode root, int low, int high) {
            if(root == null) return null;

            // 在递归中移除节点
            if (root.val < low){
                TreeNode right = trimBST(root.right, low, high);
                return right;
            }
            if (root.val > high){
                TreeNode left = trimBST(root.left, low, high);
                return left;
            }

            // root在[low,high]范围内
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);

            return root;
        }
    }
}
