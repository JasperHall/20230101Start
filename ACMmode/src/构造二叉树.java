import sun.reflect.generics.tree.Tree;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1020
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/30 20:16
 */
public class 构造二叉树 {

    public static Map<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String s = sc.nextLine();  // 输入一行, 前序和中序用空格分隔
            String[] ss = s.split(" ");
            String pre = ss[0];  // 前序
            String in = ss[1];  // 中序

            // 构建二叉树
            // TreeNode preorder = build(pre.toCharArray());
            // TreeNode inorder = build(in.toCharArray());

            // 前序中序生成原来的二叉树
            CharTreeNode res = afterHelper(pre.toCharArray(), in.toCharArray());
            // 打印二叉树
            printTree(res);
            System.out.println();
        }
    }

    /**
     *
     * @param pre 前序数组
     * @param in 中序数组
     * @return
     */
    public static CharTreeNode afterHelper(char[] pre, char[] in){
        for (int i=0; i<in.length; i++){  // 使用后序遍历的数组构建map
            map.put(in[i], i);
        }
        CharTreeNode res = helper(pre, 0, pre.length-1, in, 0, in.length-1);
        return  res;
    }

    /**
     * 根据前序和中序遍历数组来生成二叉树
     * @param pre 前序数组
     * @param ps 前序数组开始
     * @param pe 前序数组结束
     * @param in 后续数组
     * @param is 后续数组开始
     * @param ie 后续数组结束
     * @return
     */
    public static CharTreeNode helper(char[] pre, int ps, int pe, char[] in, int is, int ie){
        // 前序分割起点<0, 前序分割起点>终点, 后续分割起点<0, 后续分割起点>终点
        if (ps<0 || ps>pe || is<0 || is>ie) return null;
        // 前序终点超出数组范围    后续起点超出数组范围
        if (pe>pre.length-1 || ie>in.length-1) return null;

        char headVal = pre[ps]; // 获取前序遍历的第一个值为根节点
        int headIndex = map.get(headVal);  // 从中序遍历的map中取到根节点的位置
        int lengthOfLeft = headIndex - is;  // 左子树的长度(节点个数)
        CharTreeNode root = new CharTreeNode(headVal);  // 使用前序遍历的头节点的值创建二叉树

        root.left = helper(pre, ps+1, ps+lengthOfLeft, in, is, headIndex-1);
        root.right = helper(pre, ps+1+lengthOfLeft, pe, in, headIndex+1, ie);

        return  root;
    }

    /**
     * 后序遍历打印二叉树
     * @param root
     */
    public static void printTree(CharTreeNode root){
        if (root == null) return;
        printTree(root.left);  // 遍历左子树
        printTree(root.right);  // 遍历右子树
        System.out.print(root.val);  // 不换行输出节点的值
    }

}
/*

class TreeNode{
    TreeNode left;
    TreeNode right;
    Character val;
    public TreeNode(Character val){
        this.val = val;
    }
}
*/
