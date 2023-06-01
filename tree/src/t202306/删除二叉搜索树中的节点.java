package t202306;

/**
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/1 20:19
 */
public class 删除二叉搜索树中的节点 {  // 450. 删除二叉搜索树中的节点

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return root;  // 第一种情况：没找到删除的节点，遍历到空节点直接返回了
            if (root.val == key) {
                // 第二中藏在第三种和第四种之间
                if (root.left == null) {  // 第三种情况：其左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
                    return root.right;
                } else if (root.right == null) {  // 第四种情况：其右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
                    return root.left;
                } else {
                    // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
                    // 并返回删除节点右孩子为新的根节点。
                    TreeNode cur = root.right;
                    // 找右子树最左面的节点
                    while (cur.left != null) {
                        cur = cur.left;
                    }
                    cur.left = root.left;  // 把要删除的节点（root）左子树放在cur的左孩子的位置
                    root = root.right;  // 返回旧root的右孩子作为新root
                    return root; // 返回
                }
            }
            if (root.val > key) root.left = deleteNode(root.left, key);
            if (root.val < key) root.right = deleteNode(root.right, key);
            return root;
        }
    }
}
