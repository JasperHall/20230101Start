package t202305;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/27 21:16
 */
public class 二叉树的中序遍历 {  // 94
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }
        public void inorder(TreeNode root, List<Integer> list){
            if (root == null) return;

            // 先左, 再中, 再右
            inorder(root.left, list);
            list.add(root.val);    // 注意这一句add
            inorder(root.right, list);
        }

        /**
         * 迭代法
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal2(TreeNode root) {
            List<Integer> result = new ArrayList<>();

            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()){
                if (cur != null){
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();  // 弹出
                    result.add(cur.val);
                    cur = cur.right;
                }
            }
            return result;
        }

        /**
         * 统一迭代法
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> result = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();

            if (root != null) stack.push(root);

            while (!stack.empty()){
                TreeNode node = stack.peek();
                if (node != null){
                    stack.pop();  // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中

                    if (node.right != null) stack.push(node.right);  // 添加右节点(空节点不入栈)
                    stack.push(node);  // 添加中间节点
                    stack.push(null); // 中间节点访问过, 但是还没有处理, 加入空节点作为标记

                    if (node.left != null) stack.push(node.left);  // 添加左节点（空节点不入栈）

                } else {  // 只有遇到空节点的时候，才将下一个节点放进结果集
                    stack.pop();  // 将空节点弹出
                    node = stack.peek();  // 重新获取栈中元素
                    stack.pop();
                    result.add(node.val);  // 加入到结果集
                }
            }
            return result;
        }
    }
}
