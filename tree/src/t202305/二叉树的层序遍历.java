package t202305;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/28 20:52
 */
public class 二叉树的层序遍历 {  // 102
    class Solution {

        public List<List<Integer>> resList = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            // checkFun01(root, 0);  // DFS

            checkFun02(root);  // BFS

            return resList;
        }

        //DFS--递归方式
        public void checkFun01(TreeNode node, Integer deep) {
            if (node == null) return;
            deep++;

            if (resList.size() < deep) {
                //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
                List<Integer> item = new ArrayList<Integer>();
                resList.add(item);
            }
            resList.get(deep - 1).add(node.val);

            checkFun01(node.left, deep);
            checkFun01(node.right, deep);
        }

        // BFS--迭代方式--借助队列
        public void checkFun02(TreeNode node){
            if (node == null) return;

            Queue<TreeNode> que = new LinkedList<>();
            que.offer(node);

            while (!que.isEmpty()) {  // 队列不为空就进来while循环
                List<Integer> itemList = new ArrayList<>();
                int len = que.size();  // 注意len的控制循环次数

                while (len > 0){
                    TreeNode tmpNode = que.poll(); // 出队
                    itemList.add(tmpNode.val);

                    if (tmpNode.left != null) que.offer(tmpNode.left);  // 入队
                    if (tmpNode.right != null) que.offer(tmpNode.right);

                    len--;
                }

                resList.add(itemList);
            }
        }
    }
}
