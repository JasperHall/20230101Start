import javax.swing.tree.TreeNode;
import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1021
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/30 22:11
 */
public class 二叉树的遍历 {

//    static CharTreeNode[] nodes;
    static CharTreeNode[] nodes = new CharTreeNode[30];  // 这里必须写[30], 否则会报空指针异常

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()){
            int len = sc.nextInt();
            for (int i = 0; i < len; i++) {
                char val = sc.next().charAt(0);  // 一个大写字母表示节点, 后面为两整数, 看下面的left和right
                int left = sc.nextInt();  // 左儿子序号, 如果该序号为0表示没有
                int right = sc.nextInt();  // 右儿子序号, 如果该序号为0表示没有

                // i表示层数, 当输入一组数据后下一次为null, 则new一个节点
                if (nodes[i+1] == null){
                    nodes[i+1] = new CharTreeNode(val);
                } else {  // 不为空的时候就是之前已经建好了, 但是没有写入值, 这里写入节点的值
                    nodes[i+1].val = val;
                }

                // 输入的左序号不为0
                // 有左节点时   left为左儿子序号, 如果该序号为0表示没有
                if (left != 0){
                    if (nodes[left] == null){  // 数组的left序号位置等于null
                        // 建一个节点, 此时输入到第1行, 该节点的左子树在要输入的第3行, 所以这里先把节点构建出来, 后面输入到第五次时填入val值
                        nodes[left] = new CharTreeNode('\0');
                    }
                    nodes[i+1].left = nodes[left];  // 这个新建的节点就是nodes[i+1]左子树
                }

                // 有右节点时
                if (right != 0){
                    if (nodes[right] == null){
                        nodes[right] = new CharTreeNode('\0');
                    }
                    nodes[i+1].right = nodes[right];
                }

            }

            preorder(nodes[1]); // 从 i+1 (i=0)开始的, 所以传入时也从1开始
            System.out.println();  // 记得换行
            inorder(nodes[1]);
            System.out.println();
            postorder(nodes[1]);
            System.out.println();
        }
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preorder(CharTreeNode root){
        if (root == null) return;
        System.out.print(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void inorder(CharTreeNode root){
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val);
        inorder(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void postorder(CharTreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val);
    }
}
