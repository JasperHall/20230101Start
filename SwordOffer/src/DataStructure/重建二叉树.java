package DataStructure;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/12 21:19
 */
public class 重建二叉树 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param preOrder int整型一维数组
     * @param vinOrder int整型一维数组
     * @return TreeNode类
     */
    public TreeNode reConstructBinaryTree (int[] preOrder, int[] vinOrder) {
        // write code here

        if (preOrder.length==0 || vinOrder.length==0) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[0]);  // 前序序列第一个值肯定是根节点

        // 在 中序序列 中找到 前序的根节点
        for (int i = 0; i < vinOrder.length; i++) {
            if (vinOrder[i] == preOrder[0]){
                // 左子树  注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(preOrder, 1, i + 1), Arrays.copyOfRange(vinOrder, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(preOrder, i + 1, preOrder.length), Arrays.copyOfRange(vinOrder, i + 1, vinOrder.length));
                break;
            }

        }

        return root;
    }
}
