package t202305;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/28 21:15
 */
public class 二叉树的层序遍历II {  //107
    class Solution {
        /**
         * 迭代, 借助队列
         * 层序遍历, 最后翻转数组
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            Deque<TreeNode> deque = new LinkedList<>();  // 双端队列

            if (root == null) return list;

            deque.offerLast(root);
            while (!deque.isEmpty()){
                List<Integer> levelList = new ArrayList<>();

                int levelSize = deque.size();

                while (levelSize > 0){
                    TreeNode peek = deque.peekFirst();
                    levelList.add(deque.pollFirst().val);

                    if (peek.left != null) deque.offerLast(peek.left);
                    if (peek.right != null) deque.offerLast(peek.right);

                    levelSize--;  // 不要忘记减减
                }
                list.add(levelList);
            }

            List<List<Integer>> result = new ArrayList<>();
            for (int i=list.size()-1; i>=0; i--){
                result.add(list.get(i));
            }
            return result;
        }
    }
}
