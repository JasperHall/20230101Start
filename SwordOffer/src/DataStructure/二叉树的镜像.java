package DataStructure;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.nowcoder.com/practice/a9d0ecbacef9410ca97463e4a5c83be7?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/13 21:13
 */
public class 二叉树的镜像 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pRoot TreeNode类
     * @return TreeNode类
     */
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here

        if(pRoot == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);  // 记得加入根节点

        while (!queue.isEmpty()){  // 判断队列不为空
            TreeNode node = queue.poll();// poll方法相当于移除队列头部的元素

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);

        }
        return pRoot;
    }
}
