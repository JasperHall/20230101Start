package DataStructure;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/508378c0823c423baa723ce448cbfd0c?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/14 15:42
 */
public class 二叉树中和为某一值的路径I {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @param sum int整型
     * @return bool布尔型
     */
    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        // 如果遍历到空节点一直没有过进入下面那个if语句, 说明找不到路径, 直接返回fasle
        if(root == null) return false;

        // 判断root为叶子节点时, 是不是等于了目标值
        if (root.left==null && root.right==null && sum-root.val==0) return true;

        boolean left = hasPathSum(root.left, sum-root.val);
        boolean right = hasPathSum(root.right, sum- root.val);

        return left || right;
    }

    /**
     * DFS
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2 (TreeNode root, int sum) {
        if (root == null) return false; // 空节点找不到路径
        // 栈辅助深度优先遍历
        Stack<TreeNode> stack1 = new Stack<>();
        // 跟随stack1记录到相应节点为止的路径和
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root.val);

        while (!stack1.isEmpty()){
            // 弹出相应的节点
            TreeNode temp = stack1.pop();
            // 弹出到该点为止的当前路径和
            int cur_sum = stack2.pop();

            // 判断是叶子节点且当前路径和等于sum
            if(temp.left == null && temp.right == null && cur_sum == sum) return true;

            // 左节点及路径和入栈
            if(temp.left != null){
                stack1.push(temp.left);
                stack2.push(cur_sum + temp.left.val);
            }

            // 右节点及路径和入栈
            if(temp.right != null){
                stack1.push(temp.right);
                stack2.push(cur_sum + temp.right.val);
            }
        }
        return false;
    }
}
