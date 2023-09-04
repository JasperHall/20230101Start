import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1022
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/4 11:24
 */
public class 二叉树的高度 {

    static class TreeNode{
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode(char val){
            this.val = val;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            sc.nextInt();   // 思考这里为啥这样用
            String preOrder = sc.next();  // 前序
            String inOrder = sc.next();  // 中序

            TreeNode root = builderTree(preOrder, inOrder);  // 构建树
            int height = getHeight(root);  // 此处已经构建好二叉树root, 获取树的高度
            System.out.println(height);
        }
        sc.close();
    }

    /**
     * 递归构建二叉树
     * @param preOrder 前序遍历
     * @param inOrder 后续变量
     * @return
     */
    private static TreeNode builderTree(String preOrder, String inOrder){
        if (preOrder.isEmpty()) return null;

        char rootVal = preOrder.charAt(0);   // 从前序遍历字符串中取出
        TreeNode root = new TreeNode(rootVal);  // 构建父结点

        int rootIndex = inOrder.indexOf(rootVal);  // 返回当前rootVal在inOrder(中序)里第一次出现的索引

        String leftPreOrder = preOrder.substring(1, rootIndex+1);  // 前序左子树
        String leftInOrder = inOrder.substring(0, rootIndex);  // 中序左子树

        root.left = builderTree(leftPreOrder, leftInOrder);  // 构建左子树

        String rightPreOrder = preOrder.substring(rootIndex + 1);  // 前序右子树
        String rightInorder = inOrder.substring(rootIndex + 1);  // 中序右子树

        root.right  = builderTree(rightPreOrder, rightInorder);  // 构建右子树

        return root;
    }

    /**
     * 获取二叉树的高度
     * 注意理解这个计算高度的方法
     * @param root
     * @return
     */
    private static int getHeight(TreeNode root) {
        if (root == null) return 0;

        // 一直递归到最后叶子节点返回0, 然后递归向上走, 每次比较左右子树的高 返回最大值+1
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
