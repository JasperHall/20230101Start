package t202305;

import com.sun.source.tree.Tree;

import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/count-complete-tree-nodes/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/29 20:40
 */
public class 完全二叉树的节点个数 {  //222.
    class Solution {
        /**
         * 普通二叉树的方式, 递归法
         * 后序遍历
         * @param root
         * @return
         */
        public int countNodes(TreeNode root) {
            if (root == null) return 0;

            int leftNum = countNodes(root.left);  // 左
            int rightNum = countNodes(root.right);  // 右
            int treeNum = leftNum + rightNum + 1; // 中

            return treeNum;
        }

        /**
         * 普通二叉树的迭代法
         * 把层序遍历的模板稍作改动
         * @param root
         * @return
         */
        public int countNodes2(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int result = 0;
            while (!queue.isEmpty()){
                int size = queue.size();

                while (size-- > 0){
                    TreeNode cur = queue.poll();
                    result++;
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
            }
            return result;
        }


        /**
         * 针对完全二叉树的解法
         * 满二叉树的节点数为: 2^depth-1
         * @param root
         * @return
         */
        public int countNodes3(TreeNode root) {
            if (root == null) return 0;

            // 开始根据左深度和右深度是否相同来判断该子树是不是满二叉树
            TreeNode leftNode = root.left;
            TreeNode rightNode = root.right;
            int leftDepth = 0, rightDepth = 0;  // 这里初始为0是有目的的，为了下面求指数方便

            while (leftNode != null){  // 求左子树的深度
                leftNode = leftNode.left;
                leftDepth++;
            }
            while (rightNode != null){  // 求右子树的深度
                rightNode = rightNode.right;
                rightDepth++;
            }

            if (leftDepth == rightDepth) return (2 << leftDepth) - 1;  // 注意(2<<1) 相当于2^2，所以leftDepth初始为0

            int leftTreeNum = countNodes3(root.left);  // 左
            int rightTreeNum = countNodes3(root.right);  // 右
            int result = leftTreeNum + rightTreeNum + 1;  // 中, 注意这里 +1
            return result;

        }

    }
}
