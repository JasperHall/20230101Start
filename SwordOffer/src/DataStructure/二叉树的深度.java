package DataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/11 19:22
 */
public class 二叉树的深度 {

    /**
     * 递归 前序遍历
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        // 空节点没有深度
        if (root == null) return 0;

        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public int TreeDepth2(TreeNode root) {
        // 空节点没有深度
        if (root == null) return 0;

        // 队列维护层次后续节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);  // 根节点入队
        int res = 0; // 记录深度

        // 层次遍历
        while (!queue.isEmpty()){
            int n = queue.size(); // 记录当前层有多少个节点
            // 遍历完这一层，再进入下一层
            for (int i=0; i<n; i++){   // 这里i从0开始, 所以 i<n
                TreeNode node = queue.poll(); // 弹出
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res++; // 深度+1
        }
        return res;
    }
}
