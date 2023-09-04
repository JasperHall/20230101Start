import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * https://kamacoder.com/problem.php?id=1022@Version 1.0
 *
 * 方法二：递归（使用哈希表来优化中序遍历中查找根节点位置的过程）
 * @Author:MenFanys
 * @Date:2023/9/4 15:05
 */
public class 二叉树的高度2 {

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

        while (sc.hasNext()){
            int N = sc.nextInt();
            String preOrder = sc.next();
            String inOrder = sc.next();

            HashMap<Character, Integer> inOrderMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                inOrderMap.put(inOrder.charAt(i), i);
            }

            TreeNode root = buildTree(preOrder, 0, N-1, 0, N-1, inOrderMap);
            int height = getHeight(root);
            System.out.println(height);
        }
        sc.close();
    }

    /**
     * 构建二叉树
     * @param preOrder 前序遍历字符串
     * @param preStart 前序字符串起点
     * @param preEnd 前序字符串终点
     * @param inStart 中序遍历字起点
     * @param inEnd 中序遍历终点
     * @param inOrderMap 中序遍历Map
     * @return 构建的二叉树
     */
    private static TreeNode buildTree(String preOrder, int preStart, int preEnd, int inStart, int inEnd,
                                      HashMap<Character, Integer> inOrderMap) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        char rootVal = preOrder.charAt(preStart);
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inOrderMap.get(rootVal);  // 获取当前父节点的value, 也就是在中序遍历字符串中的位置
        int leftSubtreeSize = rootIndex - inStart;  // 左子树遍历字符串长度, 注意 减去中序遍历的起点

        // 在左子树, 中序遍历序列的起点不动, 终点每次减1
        root.left = buildTree(preOrder, preStart + 1, preStart + leftSubtreeSize, inStart, rootIndex - 1, inOrderMap);
        // 在右子树, 中序遍历序列的起点每次+1, 终点不动
        root.right = buildTree(preOrder, preStart + leftSubtreeSize + 1, preEnd, rootIndex + 1, inEnd, inOrderMap);

        return root;
    }

    /**
     * 获取二叉树的高度
     * @param root
     * @return
     */
    private static int getHeight(TreeNode root){
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight)+1;
    }
}
