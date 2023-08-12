package DataStructure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/57aa0bab91884a10b5136ca2c087f8ff?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/11 22:39
 */
public class 二叉搜索树的第k个节点 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * 中序遍历 非递归
     * @param proot TreeNode类
     * @param k int整型
     * @return int整型
     */
    public int KthNode (TreeNode proot, int k) {
        // write code here
        if(proot == null) return -1;
        // 中序遍历, k个节点
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(proot);
        TreeNode node = proot;
        int i = 0;  // 计数

        // 不等于空就一直循环
        while (!deque.isEmpty()){
            // 遍历node下的所有左节点
            while (node.left != null){
                deque.push(node.left);
                node = node.left;
            }
            i++;
            if (i==k) return deque.pop().val;  // 找到第k个, 返回值

            TreeNode temp = deque.pop();//弹出
            while (temp.right != null){
                deque.push(temp.right);
                node = temp.right;
            }
        }
        return -1;
    }

    /**
     * 递归
     * @param proot
     * @param k
     * @return
     */
    int count = 0;    //标记遍历的节点数
    int result = -1;
    public int KthNode2 (TreeNode proot, int k) {
        if(proot == null || k <= 0)    return -1;

        KthNode2(proot.left, k);  // 遍历左子树

        count++;

        if(count == k)    return result = proot.val;

        KthNode2(proot.right, k);  // 遍历右子树

        return result;
    }
}
