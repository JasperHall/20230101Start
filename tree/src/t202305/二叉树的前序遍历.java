package t202305;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/27 21:05
 */
public class 二叉树的前序遍历 {  // 144


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        /**
         * 递归法
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preorder(root, result);
            return result;

        }
        public void preorder(TreeNode root, List<Integer> result){
            if (root == null) return;

            result.add(root.val);
            preorder(root.left, result);
            preorder(root.right, result);

        }

        /**
         * 迭代法
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal2(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()){
                TreeNode node = stack.pop();
                result.add(node.val);

                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }

            return result;
        }

        /**
         * 统一迭代法
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal3(TreeNode root) {
            List<Integer> result = new LinkedList<>();
            Stack<TreeNode> st = new Stack<>();
            if (root != null) st.push(root);
            while (!st.empty()) {
                TreeNode node = st.peek();
                if (node != null) {
                    st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                    if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                    if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
                    st.push(node);                          // 添加中节点
                    st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

                } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                    st.pop();           // 将空节点弹出
                    node = st.peek();    // 重新取出栈中元素
                    st.pop();
                    result.add(node.val); // 加入到结果集
                }
            }
            return result;
        }

    }
}
