package t202306;


/**
 * 二叉树类的声明
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 21:44
 */
public class BinaryTree {
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
            if (value < currentNode.val){ //在左子树
                if (currentNode.left == null){
                    currentNode.left = new TreeNode(value);
                    return;
                }else {
                    currentNode = currentNode.left;
                }
            }else {  //在右子树
                if (currentNode.right == null){
                    currentNode.right = new TreeNode(value);
                    return;
                }else {
                    currentNode = currentNode.right;
                }
            }
        }
    }
}
