package DataStructure;

import Algorithm.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/13 21:52
 */
public class 从上往下打印二叉树 {

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;  // 注意这里一定要是返回res

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);



        while (!queue.isEmpty()){
            TreeNode node = queue.poll(); // 弹出队首
            res.add(node.val);

            if (node.left!=null) queue.add(node.left);
            if (node.right!=null) queue.add(node.right);
        }

        return res;
    }
}
