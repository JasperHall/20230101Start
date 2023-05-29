package t202305;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/invert-binary-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/28 21:47
 */
public class 翻转二叉树 {  // 226
    class Solution {
        /**
         * BFS  层序遍历
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {

            Deque<TreeNode> deque = new LinkedList<>();  // 双端队列

            if (root == null) return root;

            deque.offerLast(root);
            while (!deque.isEmpty()){
                int len = deque.size();

                while (len-- > 0){
                    TreeNode temp = deque.pollFirst();

                    swap(temp);

                    if (temp.left != null) deque.offerLast(temp.left);
                    if (temp.right != null) deque.offerLast(temp.right);
                }
            }
            return root;
        }
        public void swap(TreeNode root){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }


        /**
         * DFS 递归
         * @param root
         * @return
         */
        public TreeNode invertTree2(TreeNode root) {
            // 终止条件
            if (root == null) return null;  // 这里写NULL或者写root都行

            invertTree2(root.left);
            invertTree2(root.right);
            swapChildren(root);
            return root;

        }
        private void swapChildren(TreeNode root){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

    }
}
