import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/2 10:39
 */
//https://leetcode.cn/problems/binary-tree-preorder-traversal/
/*public class 二叉树的前序遍历 {

    *//**
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
     *//*
    class Solution {
        //方法一：递归
        public List<Integer> preorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            preorder1(root, res);
            return res;
        }

        private void preorder1(TreeNode root, List<Integer> res) {
            if (root == null){
                return;
            }
            res.add(root.val);
            preorder(root.left, res);
            preorder(root.right, res);
        }
        //方法二：迭代
        public List<Integer> preorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            TreeNode node = root;
            while (!stack.isEmpty() || node != null) {
                while (node != null) {
                    res.add(node.val);
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                node = node.right;
            }
            return res;
        }
    }
}*/
