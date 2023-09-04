/**
 * 节点为char的二叉树
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/3 21:17
 */
public class CharTreeNode {
    Character val;


    CharTreeNode left;
    CharTreeNode right;


    CharTreeNode(Character val){ this.val = val;}

    CharTreeNode(Character val, CharTreeNode left_Node, CharTreeNode right_Node) {
        this.val = val;
        this.left = left_Node;
        this.right = right_Node;
    }
}
