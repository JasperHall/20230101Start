package t202305;

import com.sun.source.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/symmetric-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/29 0:48
 */
public class 对称二叉树 {  // 101
    class Solution {
        /**
         * 递归法
         * @param root
         * @return
         */
        public boolean isSymmetric(TreeNode root) {
            return compare(root.left, root.right);
        }
        private boolean compare(TreeNode leftNode, TreeNode rightNode){
            if(leftNode==null && rightNode!=null) return false;
            else if(leftNode!=null && rightNode==null) return false;
            else if(leftNode==null && rightNode==null) return true;
            else if(leftNode.val != rightNode.val) return false;  // 注意这里没使用else要用else if
            // 比较外侧
            boolean compareOutside = compare(leftNode.left, rightNode.right);
            // 比较内侧
            boolean compareInside = compare(leftNode.right, rightNode.left);
            return compareOutside && compareInside; // 注意这里的返回方式
        }

        /**
         * 迭代法   使用双端队列, 相当于两个栈
         * @param root
         * @return
         */
        public boolean isSymmetric2(TreeNode root) {
            Deque<TreeNode> deque = new LinkedList<>();
            deque.offerFirst(root.left);  // 这里插入对首
            deque.offerLast(root.right);  // 这里插入队尾

            while (!deque.isEmpty()) {
                TreeNode leftNode = deque.pollFirst();  // 这里是弹出队首
                TreeNode rightNode = deque.pollLast(); // 这里是弹出队尾

                if (leftNode==null && rightNode==null){
                    continue;  // 跳过本次循环
                }

                /*if (leftNode == null && rightNode != null) {
                    return false;
                }
                if (leftNode != null && rightNode == null) {
                    return false;
                }
                if (leftNode.val != rightNode.val) {
                    return false;
                }*/

                // 以上三个判断条件合并, 思考为什么可以这样合并: 上面的if已经判断的同时为null的情况, 所以接下来有一个为null将会是不等
                if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                    return false;
                }

                // 到达这里意味着每个左右节点的各自的左右孩子都不为空, 所以队首插入左节点的俩孩子, 队尾插入右节点的俩孩子,
                // 这样在接下来的循环中就可以分别从队首和队尾取出值来判断了
                deque.offerFirst(leftNode.left);
                deque.offerFirst(leftNode.right);
                deque.offerLast(rightNode.right);
                deque.offerLast(rightNode.left);

            }
            return true;  // 上边都没有return false 则最后就return true
        }

        /**
         * 迭代法   使用栈
         * @param root
         * @return
         */
        public boolean isSymmetric3(TreeNode root) {
            Stack<TreeNode> st = new Stack<>();
            st.push(root.left);
            st.push(root.right);
            while (!st.isEmpty()) {
                TreeNode leftNode = st.pop();
                TreeNode rightNode = st.pop();
                if (leftNode == null && rightNode == null) {
                    continue;
                }

                if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                    return false;
                }

                st.push(leftNode.left);
                st.push(rightNode.right);
                st.push(leftNode.right);
                st.push(rightNode.left);
            }
            return true;
        }
    }
}
