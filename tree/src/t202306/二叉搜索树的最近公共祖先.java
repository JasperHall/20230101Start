package t202306;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/1 16:28
 */
public class 二叉搜索树的最近公共祖先 {  // 235. 二叉搜索树的最近公共祖先

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return traversal(root, p, q);
        }
        private TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q){
            if (cur == null) return null;

            if (cur.val>p.val && cur.val>q.val){  // 左
                TreeNode left = traversal(cur.left, p, q);
                if (left!=null) {
                    return left;
                }
            }

            if (cur.val< p.val && cur.val<q.val){  //右
                TreeNode right = traversal(cur.right, p, q);
                if (right!=null){
                    return right;
                }
            }
            return cur;
        }
    }
}
