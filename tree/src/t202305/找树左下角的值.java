package t202305;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/30 17:55
 */
public class 找树左下角的值 {  // 513

    class Solution {

        /**
         * 递归 易懂版
         * @param root
         * @return
         */
        private int maxDepth2 = -1;  // 全局变量 记录最大深度
        private  int value2 = 0;  // 全局变量 最大深度最左节点的数值
        public int findBottomLeftValue2(TreeNode root) {
            value2 = root.val;
            findLeftValue2(root, 0);
            return value2;
        }
        private void findLeftValue2(TreeNode root, int deep){
            if (root == null) return;

            if (root.left==null && root.right==null){  // 左右都为null, 则是叶子节点
                if (deep > maxDepth2){
                    maxDepth2 = deep;  // 更新最大深度
                    value2 = root.val;  // 最大深度最左面的数值
                }
                // return;  // 有没有return都行
            }

            if (root.left != null) {
                deep++;
                findLeftValue2(root.left, deep);
                deep--;  // 回溯
            }
            if (root.right != null) {
                deep++;
                findLeftValue2(root.right, deep);
                deep--;  // 回溯
            }
            // return;   // 有没有return都行
        }


        /**
         * 递归  精简版
         * @param root
         * @return
         */
        private int maxDepth = -1;  // 全局变量 记录最大深度
        private int value = 0;  // 全局变量 最大深度最左节点的数值
        public int findBottomLeftValue(TreeNode root) {
            value = root.val;
            findLeftValue(root, 0);
            return value;
        }
        private void findLeftValue(TreeNode root, int deep){
            if (root == null) return;

            if (root.left==null && root.right==null){  // 左右都为null, 则是叶子节点
                if (deep > maxDepth){
                    maxDepth = deep;  // 更新最大深度
                    value = root.val;  // 最大深度最左面的数值
                }
            }
            if (root.left != null) findLeftValue(root.left, deep+1);  // 隐藏着回溯
            if (root.right != null) findLeftValue(root.right, deep+1);  // 隐藏着回溯
        }

        /**
         * 迭代法
         * @param root
         * @return
         */
        public int findBottomLeftValue3(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int res = 0;

            while (!queue.isEmpty()){
                int size = queue.size();

                for (int i=0; i<size; i++){
                    TreeNode tempNode = queue.poll();

                    if (i==0){
                        res = tempNode.val;
                    }

                    if (tempNode.left != null) queue.offer(tempNode.left);
                    if (tempNode.right != null) queue.offer(tempNode.right);
                }
            }
            return res;
        }

    }
}
