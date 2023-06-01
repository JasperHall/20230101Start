package t202305;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/1 14:24
 */
public class 二叉树的最近公共祖先 {
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root==null || root==p || root==q){  // 递归结束条件
                return root;
            }

            // 后序遍历
            TreeNode left = lowestCommonAncestor(root.left, p, q);  // 左
            TreeNode right = lowestCommonAncestor(root.right, p, q); // 右

            if (left==null && right==null){  // 若未找到节点 p 或 q
                return null;
            }else if (left==null && right!=null){  // 若找到一个节点
                return right;
            }else if (left!=null && right==null){
                return left;
            }else {
                return root;
            }
        }
    }
}
