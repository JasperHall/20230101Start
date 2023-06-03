package t202306;

/**
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/3 11:37
 */
public class 把二叉搜索树转换为累加树 {  // 538. 把二叉搜索树转换为累加树

    class Solution {

        int sum;
        public TreeNode convertBST(TreeNode root) {
             sum = 0;
             convertBSTDemo(root);
             return root;
        }

        // 按右中左顺序遍历，累加即可
        private void convertBSTDemo(TreeNode root){
            if (root == null){
                return;
            }

            convertBSTDemo(root.right);
            sum += root.val;
            root.val = sum;
            convertBSTDemo(root.left);
        }
    }
}
