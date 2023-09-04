import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1021
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/4 10:26
 */
public class 二叉树的遍历2 {

    /**
     * 使用索引，简化构建树的过程
     */
    static class TreeNode {
        char val;
        int left;   // 注意这里是用的int
        int right;  // 注意这里是用的int

        TreeNode(char val, int left, int right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static TreeNode[] nodes;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nodes = new TreeNode[n+1];  // 注意这里使用n+1作为数组的长度
        for (int i = 0; i < n; i++) {
            char val = sc.next().charAt(0);
            int left = sc.nextInt();
            int right = sc.nextInt();

            nodes[i+1] = new TreeNode(val, left, right);
        }

        preOrderTraversal(1);
        System.out.println();
        inOrderTraversal(1);
        System.out.println();
        postOrderTraversal(1);
        System.out.println();
        sc.close();
    }

    /**
     * 前序遍历
     * @param root
     */
    private static void preOrderTraversal(int root){
        if (root == 0) return;
        System.out.print(nodes[root].val);
        preOrderTraversal(nodes[root].left);
        preOrderTraversal(nodes[root].right);
    }

    /**
     * 后续
     * @param root
     */
    public static void postOrderTraversal(int root) {
        if (root == 0)
            return;
        postOrderTraversal(nodes[root].left);
        postOrderTraversal(nodes[root].right);
        System.out.print(nodes[root].val);
    }

    /**
     * 中序
     * @param root
     */
    private static void inOrderTraversal(int root) {
        if (root == 0)
            return;
        inOrderTraversal(nodes[root].left);
        System.out.print(nodes[root].val);
        inOrderTraversal(nodes[root].right);
    }

}
