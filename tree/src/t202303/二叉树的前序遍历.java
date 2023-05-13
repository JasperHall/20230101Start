package t202303;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 20:16
 *
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class 二叉树的前序遍历 {

    public static void main(String[] args) throws IOException {
        int tempdata;
        int [] content = new int[]{7,4,1,5,16,8,11,12,15,9,2};

        BinaryTree binaryTree = new BinaryTree(content);//创建一个二叉树声明类的对象
        System.out.println("使用链表方式建立二叉树, 成功!!!");

        System.out.println("进行二叉树前序遍历: ");
        List<Integer> list = binaryTree.preorderTraversal(binaryTree.rootNode);//传入参数, 进行前序遍历, 返回列表
        for (Integer integer : list) {
            System.out.print("["+ integer + "] ");
        }


    }


    static class BinaryTree {

        public TreeNode rootNode; //二叉树的根节点
        //构造函数: 利用传入一个数组的参数来建立二叉树
        public BinaryTree(int[] data){
            for (int i=0; i<data.length; i++){
                Add_Node_To_Tree(data[i]);
            }
        }

        //将指定的值加入到二叉树中适当的节点
        void Add_Node_To_Tree(int value){
            TreeNode currentNode = rootNode;
            if (rootNode == null){  //建立树根
                rootNode = new TreeNode(value);
                return;
            }

            //建立二叉树
            while (true){
                if (value < currentNode.value){ //在左子树
                    if (currentNode.left_Node == null){
                        currentNode.left_Node = new TreeNode(value);
                        return;
                    }else {
                        currentNode = currentNode.left_Node;
                    }
                }else {  //在右子树
                    if (currentNode.right_Node == null){
                        currentNode.right_Node = new TreeNode(value);
                        return;
                    }else {
                        currentNode = currentNode.right_Node;
                    }
                }
            }
        }

        /**
         * 递归实现二叉树前序遍历
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            preorder(root, result);
            return result;
        }
        public void preorder(TreeNode root, List<Integer> result){
            if (root == null){
                return;
            }
            result.add(root.value);//注意这里的 result.add的作用
            preorder(root.left_Node, result);
            preorder(root.right_Node, result);
        }


    }
}
